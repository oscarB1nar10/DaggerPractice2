package com.example.daggerpractice2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggerpractice2.models.User
import com.example.daggerpractice2.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){
     val TAG  = "SessionManager"
    //vars
    private val cachedUser : MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun AuthenticateWithId(source: LiveData<AuthResource<User>>){
        if(source != null){
            cachedUser.value = AuthResource.loading(null as User?)
            cachedUser.addSource(source) {
                cachedUser.value = it
                cachedUser.removeSource(source)
            }
        }
    }

    fun logOut(){
        Log.i(TAG, "logOut: logging out....")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser() : LiveData<AuthResource<User>>{
        return  cachedUser
    }
}