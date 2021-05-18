package com.codetrade.app.ui.Util.googleplus

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class GooglePlus {

    private var activity: AppCompatActivity
    private var RC_SIGN_IN = 5
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var callBackGooglePlus: CallBackGooglePlus? = null

    @Inject
    constructor(activity: AppCompatActivity) {
        this.activity = activity
        initialize()
    }

    private fun initialize() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun signOut() {
        if (mGoogleSignInClient != null) {
            mGoogleSignInClient!!.signOut()
        }
    }

    fun onLogin(callBackGooglePlus: CallBackGooglePlus) {

        mGoogleSignInClient!!.signOut().addOnCompleteListener {
            this.callBackGooglePlus = callBackGooglePlus
            val signInIntent = mGoogleSignInClient!!.signInIntent
            activity.startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.let {
                callBackGooglePlus?.let {
                    if (account != null) {
                        if (account.id != null)
                            callBackGooglePlus!!.onSuccess(account)
                    }
                }

            }
        } catch (e: ApiException) {
            Log.w("Error G+", "signInResult:failed code=" + e.statusCode)
            callBackGooglePlus!!.onError(e.message.toString())
        }

    }


}
