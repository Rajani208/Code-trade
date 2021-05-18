package com.codetrade.app.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.codetrade.app.AuthenticationActivity
import com.codetrade.app.HomeActivity
import com.codetrade.app.R
import com.codetrade.app.core.Validator
import com.codetrade.app.data.pojo.User
import com.codetrade.app.di.component.FragmentComponent
import com.codetrade.app.exception.ApplicationException
import com.codetrade.app.ui.Util.googleplus.CallBackGooglePlus
import com.codetrade.app.ui.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.auth_login_main.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var validator: Validator

    private lateinit var auth: FirebaseAuth

    override fun createLayout(): Int = R.layout.auth_login_main


    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun bindData() {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        textViewSubmit.setOnClickListener(this)
        textViewSignup.setOnClickListener(this)
        textViewGoogle.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0) {
            textViewSubmit -> {
                if (isValidate()) {
                    navigator.toggleLoader(true)
                    auth.signInWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString()).addOnCompleteListener(activity!!) { task ->
                        navigator.toggleLoader(false)
                        if (task.isSuccessful) {
                            val fireBaseUser = auth.currentUser
                            if (fireBaseUser!!.isEmailVerified) {
                                val user = User("", editTextEmail.text.toString(), "Simple")
                                session.user = user
                                navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
                            } else {
                                fireBaseUser.sendEmailVerification()
                                showMessage("Check your email")
                            }

                        } else {
                            showMessage("Login Failed")
                        }
                    }
                }
            }
            textViewSignup -> {
                navigator.load(SignUpFragment::class.java).add(true)
            }
            textViewGoogle -> {
                (activity as AuthenticationActivity).googlePlus.onLogin(object : CallBackGooglePlus {
                    override fun onSuccess(googleSignInAccount: GoogleSignInAccount) {
                        val user = User(googleSignInAccount.displayName!!, googleSignInAccount.email!!, "Google")
                        session.user = user
                        navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
                    }

                    override fun onError(message: String) {
                        Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun isValidate(): Boolean {
        try {
            validator.submit(editTextEmail)
                    .checkEmpty().errorMessage(R.string.enter_email)
                    .checkValidEmail().errorMessage(R.string.enter_valid_email)
                    .check()
            validator.submit(editTextPassword).checkEmpty().errorMessage(R.string.enter_password).check()

        } catch (e: ApplicationException) {
            showMessage(e.message)
            return false
        }
        return true
    }

}