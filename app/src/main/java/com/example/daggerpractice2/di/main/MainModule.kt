package com.example.daggerpractice2.di.main

import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerpractice2.network.main.MainApi
import com.example.daggerpractice2.ui.main.post.PostRecyclerAdapter
import com.example.daggerpractice2.util.VerticalSpaceItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideItemDecorationToRecycler(): VerticalSpaceItemDecoration{
        return VerticalSpaceItemDecoration(15)
    }

    @MainScope
    @Provides
    fun provideLinearLayoutManager (application: Application) : LinearLayoutManager{
        return LinearLayoutManager(application)
    }

    @Provides
    fun provideAdapter(): PostRecyclerAdapter{
        return PostRecyclerAdapter()
    }

    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi{
        return retrofit.create(MainApi::class.java)
    }
}