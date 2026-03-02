package com.lw.mynotes.featurenote.services

import android.provider.Settings.Secure.getString
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

class AuthenticationService {

    var googleIdOption = GetGoogleIdOption.Builder()
//        .setServerClientId(getString)
        .setServerClientId("629986036071-t5g3bkonete3kkvhkd9ost0ktcv39dpj.apps.googleusercontent.com") // TODO:
        .setFilterByAuthorizedAccounts(true)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()



}