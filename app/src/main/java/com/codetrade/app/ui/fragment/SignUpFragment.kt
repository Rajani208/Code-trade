package com.codetrade.app.ui.fragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codetrade.app.HomeActivity
import com.codetrade.app.R
import com.codetrade.app.core.Validator
import com.codetrade.app.data.pojo.User
import com.codetrade.app.di.component.FragmentComponent
import com.codetrade.app.exception.ApplicationException
import com.codetrade.app.ui.Util.Utility
import com.codetrade.app.ui.base.BaseFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.auth_login_main.*
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.editTextEmail
import kotlinx.android.synthetic.main.fragment_sign_up.editTextPassword
import kotlinx.android.synthetic.main.fragment_sign_up.textViewSubmit
import javax.inject.Inject


class SignUpFragment : BaseFragment() {

    private lateinit var auth: FirebaseAuth

    @Inject
    lateinit var validator: Validator

    override fun createLayout(): Int = R.layout.fragment_sign_up

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun bindData() {
        textViewSubmit.setOnClickListener {
            if (isValidate()) {
                navigator.toggleLoader(true)
                auth.createUserWithEmailAndPassword(editTextEmail.text.toString(), editTextPassword.text.toString()).addOnCompleteListener(activity!!) { task ->
                    navigator.toggleLoader(false)
                    if (task.isSuccessful) {
                        val user = User(editTextUserName.text.toString(), editTextEmail.text.toString(), "Simple")
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(auth.currentUser!!.uid)
                                .setValue(user).addOnCompleteListener { p0 ->
                                    if (p0.isSuccessful) {
                                        session.user = user
                                        navigator.loadActivity(HomeActivity::class.java).byFinishingAll().start()
                                    } else {
                                        showMessage("Authentication failed.")

                                    }
                                }
                    } else {
                        showMessage("Authentication failed.")
                    }
                }
            }
        }
    }

    private fun isValidate(): Boolean {
        try {
            validator.submit(editTextUserName)
                    .checkEmpty().errorMessage(R.string.enter_name)
                    .matchPatter(Utility.NAME_PATTERN).errorMessage(getString(R.string.error_message_please_enter_valid_name))
                    .check()

            validator.submit(editTextEmail)
                    .checkEmpty().errorMessage(R.string.enter_email)
                    .checkValidEmail().errorMessage(R.string.enter_valid_email)
                    .check()

            validator.submit(editTextPassword)
                    .checkEmpty().errorMessage(R.string.enter_password)
                    .checkMinDigits(Utility.PASS_MIN_LENGTH).errorMessage(R.string.enter_4_password)
                    .check()

            validator.submit(editTextConfirmPassword).checkEmpty().errorMessage(R.string.enter_confirm_password).check()
            validator.submit(editTextConfirmPassword).matchString(editTextPassword.text.toString())
                    .errorMessage(R.string.password_mismatch).check()

        } catch (e: ApplicationException) {
            showMessage(e.message)
            return false
        }
        return true
    }

}