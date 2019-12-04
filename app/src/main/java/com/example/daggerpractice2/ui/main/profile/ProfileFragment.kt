package com.example.daggerpractice2.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.daggerpractice2.R
import com.example.daggerpractice2.models.User
import com.example.daggerpractice2.ui.auth.AuthResource
import com.example.daggerpractice2.viewmodels.ViewModelProvFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment(){
    //const
    val TAG = "ProfileFragment"
    //vars
    lateinit var profileViewModel: ProfileViewModel
    @Inject
    lateinit var viewModelProvFactory: ViewModelProvFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Toast.makeText(activity, "profile fragment", Toast.LENGTH_SHORT ).show()
        return inflater.inflate(R.layout.fragment_profile, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG,"ProfileFragment was created...")
        profileViewModel = ViewModelProviders.of(this,viewModelProvFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED -> {setUserDetails(it.data)}
                    AuthResource.AuthStatus.ERROR ->{setErrorDetail(it.message)}
                    else -> {}
                }
            }
        })
    }

    private fun setErrorDetail(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"

    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.userName
        website.text = data?.webSite
    }

}