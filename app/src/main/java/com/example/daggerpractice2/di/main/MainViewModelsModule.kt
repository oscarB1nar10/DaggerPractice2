package com.example.daggerpractice2.di.main

import androidx.lifecycle.ViewModel
import com.example.daggerpractice2.di.ViewModelKey
import com.example.daggerpractice2.ui.main.post.PostViewModel
import com.example.daggerpractice2.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule{
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindAuthViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}