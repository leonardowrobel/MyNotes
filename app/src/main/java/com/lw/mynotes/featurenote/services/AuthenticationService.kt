package com.lw.mynotes.featurenote.services

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.lw.mynotes.featurenote.data.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthenticationService {
//    var googleIdOption = GetGoogleIdOption.Builder()
//        .setServerClientId(BuildConfig.GOOGLE_CLIENT_IP)
//        .setFilterByAuthorizedAccounts(true)
//        .build()
//
//    val request = GetCredentialRequest.Builder()
//        .addCredentialOption(googleIdOption)
//        .build()


    val currentUserFlow: Flow<User?>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser.toUser())
                }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
        }

    val currentUser: User
        get() = Firebase.auth.currentUser.toUser()

    val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    fun getUserProfile(): User {
        return Firebase.auth.currentUser.toUser()
    }

    suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }

    suspend fun linkAccountWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.currentUser!!.linkWithCredential(firebaseCredential).await() // TODO: Check, could get no user at all
    }

    suspend fun signOut() {
        Firebase.auth.signOut()
    }

    private fun FirebaseUser?.toUser(): User {
        return if (this == null) User() else User(
            id = this.uid,
            email = this.email ?: "",
            provider = this.providerId,
            displayName = this.displayName ?: "",
            isAnonymous = this.isAnonymous
        )
    }
}