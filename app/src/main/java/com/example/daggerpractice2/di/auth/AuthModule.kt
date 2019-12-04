package com.example.daggerpractice2.di.auth

import com.example.daggerpractice2.network.Auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class AuthModule {

    @Module
    companion object {

        @JvmStatic
        @AuthScope
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi{
            return retrofit.create(AuthApi::class.java)
        }

    }



}