package com.lw.mynotes.featurenote.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.R
import com.lw.mynotes.featurenote.ui.components.AuthenticationButton
import com.lw.mynotes.featurenote.ui.util.NavigationItem
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontWeight
import com.lw.mynotes.featurenote.data.model.User

// TODO: Organize design/theme systems
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
){
    val user by viewModel.user.collectAsState(initial = User())

    LaunchedEffect(Unit) {
//        viewModel.getProfile()
        viewModel.navigationEvents.collect{ navEvent ->
            when(navEvent) {
                is NavigationEvent.NavigateToMain -> {
                    navController.navigate(NavigationItem.MainNotes.route)
                }
            }
        }
    }

//    if(state.errorType != ErrorType.NO_ERROR){
//        Toast.makeText(
//            mContext,
//            state.errorMessage,
//            Toast.LENGTH_SHORT
//        ).show()
//        viewModel.clearError()
//    }

//    if(state.message.isNotEmpty()){
//        Toast.makeText(
//            mContext,
//            state.message,
//            Toast.LENGTH_SHORT
//        ).show()
//        viewModel.clearMessage()
//    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary)
                }, actions = {
                    if(!user.isAnonymous){
                        IconButton(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(horizontal = 18.dp, vertical = 10.dp),
                            onClick =  { viewModel.onSignOut() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Profile",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp),
                actions = {
                    if(user.isAnonymous){
                        AuthenticationButton(buttonText = R.string.sign_in_with_google) { credential ->
                            viewModel.onSignInWithGoogle(credential)
                        }
                    }
                }, containerColor = MaterialTheme.colorScheme.surface
            )
        }
    ) { innerPadding ->
        Surface(
            Modifier
                .fillMaxSize()
        ) {
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 18.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f, true)
                    ) {
                        Spacer(modifier = Modifier.size(12.dp))
                        if(user.isAnonymous){
                            Text("Please log in to access your information!")
                        } else {
                            Text("Welcome back, " + user.displayName + ".")
                        }
                    }
                }
            }
        }
    }
}