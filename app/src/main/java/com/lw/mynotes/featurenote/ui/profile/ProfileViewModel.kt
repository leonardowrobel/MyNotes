package com.lw.mynotes.featurenote.ui.profile

import android.util.Log
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.lw.mynotes.featurenote.services.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NavigationEvent {
    class NavigateToMain: NavigationEvent()
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val authenticationService: AuthenticationService
): ViewModel() {

    private val _navigationEvents = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

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

    fun onSignUpWithGoogle(credential: Credential, openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch {
            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                authenticationService.linkAccountWithGoogle(googleIdTokenCredential.idToken)
//                openAndPopUp(NOTES_LIST_SCREEN, SIGN_UP_SCREEN)
            } else {
//                Log.e(ERROR_TAG, UNEXPECTED_CREDENTIAL)
            }
        }
    }

    companion object {
        private const val TAG = "PROFILE_VM"
    }
}