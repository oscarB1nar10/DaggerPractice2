package com.example.daggerpractice2.network.Auth

import com.example.daggerpractice2.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id : Int) : Flowable<User>
}