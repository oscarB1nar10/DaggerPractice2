package com.example.daggerpractice2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.daggerpractice2.models.User
import com.example.daggerpractice2.ui.auth.AuthActivity
import com.example.daggerpractice2.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){
    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        sessionManager.getAuthUser().observe(this@BaseActivity, Observer<AuthResource<User>> {
            it.let {
                when(it.status){
                    AuthResource.AuthStatus.LOADING -> {}
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.e(TAG, "onChanged: LOGIN SUCCESS: ${it.data?.email}")}
                    AuthResource.AuthStatus.ERROR -> {
                        Log.e(TAG, "onChanged: ERROR SUCCESS: ${it.message}")}
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {navLoginScreen()}
                }
            }
        })
    }

    private fun navLoginScreen(){
        val intent = Intent(this@BaseActivity, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}