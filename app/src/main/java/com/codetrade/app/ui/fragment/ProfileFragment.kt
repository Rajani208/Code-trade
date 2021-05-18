package com.codetrade.app.ui.fragment

import android.view.View
import com.codetrade.app.AuthenticationActivity
import com.codetrade.app.R
import com.codetrade.app.data.pojo.User
import com.codetrade.app.di.component.FragmentComponent
import com.codetrade.app.ui.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment() {


    override fun createLayout(): Int = R.layout.fragment_profile

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun bindData() {

        if (session.user!!.loginType != "Google") {
            val user = FirebaseAuth.getInstance().currentUser!!
            val ref = FirebaseDatabase.getInstance().getReference("Users")
            val id = user.uid

            navigator.toggleLoader(true)
            ref.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    navigator.toggleLoader(false)
                    if (user != null) {
                        textViewName.text = user.name
                        textViewEmail.text = user.email
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    showMessage(getString(R.string.other_exception))
                }
            })
        }

        buttonViewLogout.setOnClickListener {
            session.clearSession()
            if (session.user!!.loginType != "Google") {
                FirebaseAuth.getInstance().signOut()
            }
            navigator.loadActivity(AuthenticationActivity::class.java).byFinishingAll().start()
        }
    }

}