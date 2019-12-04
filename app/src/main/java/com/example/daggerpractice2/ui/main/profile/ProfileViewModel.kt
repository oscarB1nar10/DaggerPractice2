package com.example.daggerpractice2.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice2.SessionManager
import com.example.daggerpractice2.models.User
import com.example.daggerpractice2.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor( sessionManager: SessionManager): ViewModel(){
    //const
    val TAG = "profileViewModel"
    //vars
    private var sessionManager: SessionManager = sessionManager

    init {
        Log.i(TAG,"ProfileViewModel injection work")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }



}