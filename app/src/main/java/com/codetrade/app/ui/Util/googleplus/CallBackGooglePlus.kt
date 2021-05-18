package com.codetrade.app.ui.Util.googleplus

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface CallBackGooglePlus {
    fun onSuccess(googleSignInAccount: GoogleSignInAccount)
    fun onError(message: String)
}