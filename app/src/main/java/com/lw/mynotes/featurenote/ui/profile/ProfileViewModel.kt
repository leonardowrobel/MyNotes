package com.lw.mynotes.featurenote.ui.profile

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.lw.mynotes.featurenote.data.model.User
import com.lw.mynotes.featurenote.services.AuthenticationService
import com.lw.mynotes.featurenote.services.NotesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NavigationEvent {
    class NavigateToMain: NavigationEvent()
}

enum class ErrorType {
    NO_ERROR
}

data class ProfileUiState(
    val user: User = User(),
    val errorType: ErrorType = ErrorType.NO_ERROR,
    val errorMessage: String = "",
    val message: String = "",
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val notesService: NotesService
): ViewModel() {
    
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _navigationEvents = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    init {
        viewModelScope.launch {
            authenticationService.currentUserFlow.collect { user ->
                if (user != null) {
                    _user.value = user
                }
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            // Create Google ID Token
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Sign in to Firebase with using the token
//            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        } else {
            Log.w(TAG, "Credential is not of type Google ID!")
        }
    }

//    fun onSignUpWithGoogle(credential: Credential) {
//        viewModelScope.launch {
//            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
//                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                authenticationService.linkAccountWithGoogle(googleIdTokenCredential.idToken)
//            } else {
//                Log.e(TAG, "UNEXPECTED_CREDENTIAL")
//            }
//        }
//    }

    fun onSignInWithGoogle(credential: Credential) {
        viewModelScope.launch {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                authenticationService.signInWithGoogle(googleIdTokenCredential.idToken)
                _uiState.update { it.copy(message = "Suas notas vão ser sincronizadas!") }
                // TODO: ask if user wants to update and remove notes from local anonymous user
                notesService.sync()
            } else {
                Log.e(TAG, "UNEXPECTED_CREDENTIAL")
            }
        }
    }

    fun clearError(){
        _uiState.update { it.copy(errorType = ErrorType.NO_ERROR, errorMessage = "") }
    }

    fun clearMessage(){
        _uiState.update { it.copy(message = "") }
    }

    fun onSignOut() {
        viewModelScope.launch {
            authenticationService.signOut()
        }
    }

    companion object {
        private const val TAG = "PROFILE_VM"
    }
}