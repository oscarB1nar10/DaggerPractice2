package com.example.daggerpractice2.network.main

import com.example.daggerpractice2.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi{
    //posts?userId=1
    @GET("posts")
    fun getPostFromUser(
        @Query("userId") Id: Int):Flowable<List<Post>>

}