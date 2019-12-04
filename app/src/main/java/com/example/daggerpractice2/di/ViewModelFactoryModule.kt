package com.example.daggerpractice2.di

import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice2.viewmodels.ViewModelProvFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvFactory: ViewModelProvFactory): ViewModelProvider.Factory

}