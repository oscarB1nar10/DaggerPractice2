package com.example.daggerpractice2.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.daggerpractice2.R
import com.example.daggerpractice2.ui.main.MainActivity
import com.example.daggerpractice2.viewmodels.ViewModelProvFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {
    //vars
    private val TAG = "AuthActivity"

    lateinit var viewModel: AuthViewModel
    @Inject
    lateinit var providerFactory: ViewModelProvFactory
    @Inject
    lateinit  var  welcomeMessage: String
    @Inject
    lateinit var drawable: Drawable
    @Inject
    lateinit var requestManager: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        login_button.setOnClickListener(this)

        Log.i(TAG,welcomeMessage)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.observeAuthState().observe(this@AuthActivity, Observer {
            it.let {
                when(it.status){
                    AuthResource.AuthStatus.LOADING -> {showProgressBar(true) }
                    AuthResource.AuthStatus.AUTHENTICATED -> {showProgressBar(false)
                        onLoginSuccess()
                        Log.e(TAG, "onChanged: LOGIN SUCCESS: ${it.data?.email}")}
                    AuthResource.AuthStatus.ERROR -> {showProgressBar(false)
                        Log.e(TAG, "onChanged: ERROR SUCCESS: ${it.message}")}
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {showProgressBar(false)}
                }
            }
        })
    }

    private fun showProgressBar(isVisible: Boolean){
        when(isVisible){
            true -> {progress_bar.visibility = View.VISIBLE}
            false -> {progress_bar.visibility = View.GONE}
        }
    }

    fun setLogo(){
        requestManager
            .load(drawable)
            .into(login_logo)
    }

    private fun onLoginSuccess(){
        val intent = Intent(this@AuthActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun attempLogin() {
        if(user_id_input.text.toString().isNotEmpty()){
            viewModel.authenticateWithId(user_id_input.text.toString().toInt())
        }else{
            return
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.login_button -> {attempLogin()}
        }
    }



}

