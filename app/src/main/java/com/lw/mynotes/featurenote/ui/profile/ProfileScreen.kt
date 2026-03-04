package com.lw.mynotes.featurenote.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.R
import com.lw.mynotes.featurenote.ui.addedit.ErrorType
import com.lw.mynotes.featurenote.ui.components.AuthenticationButton
import com.lw.mynotes.featurenote.ui.main.NavigationEvent
import com.lw.mynotes.featurenote.ui.util.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController
){
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
                    Text(text = "Profile", fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }) { innerPadding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(innerPadding), color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp)
            ) {
                Spacer(modifier = Modifier.size(8.dp))

                AuthenticationButton(buttonText = R.string.sign_in_with_google) { credential ->
//                    viewModel.onSignInWithGoogle(credential, openAndPopUp)
                }
            }
        }
    }
}