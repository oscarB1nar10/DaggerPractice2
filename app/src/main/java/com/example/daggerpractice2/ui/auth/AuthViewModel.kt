package com.example.daggerpractice2.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.daggerpractice2.SessionManager
import com.example.daggerpractice2.models.User
import com.example.daggerpractice2.network.Auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor( authApi: AuthApi, sessionManager: SessionManager) : ViewModel(){
    //const
     private val TAG = "AuthViewModelJava"
    //vars
     private var authApi: AuthApi = authApi
    private var sessionManager: SessionManager = sessionManager

     init {
         Log.i(TAG,"AuthViewModelJava: ViewModel is working ...")
         if(authApi == null){
             Log.i(TAG,"AuthViewModelJava: AuthApi is NULL")
         }else{
             Log.i(TAG,"AuthViewModelJava: AuthApi is NOT NULL")
         }

     }

    fun authenticateWithId(userId: Int){
        Log.i(TAG,"authenticateWithId: attempting to login")
        sessionManager.AuthenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>>{
        return LiveDataReactiveStreams.fromPublisher(
        authApi.getUser(userId)
            .onErrorReturn {
                val errorUser = User()
                errorUser.id = -1
                errorUser
            }
            .map {
                when(it.id){
                    -1 ->  AuthResource.error("Could not authenticate", null as User)
                    else ->  AuthResource.authenticated(it)
                }
            }
            .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>>{
        return sessionManager.getAuthUser()
    }

}


