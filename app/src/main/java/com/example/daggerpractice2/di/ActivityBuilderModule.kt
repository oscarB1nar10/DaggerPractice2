package com.example.daggerpractice2.di

//import com.example.daggerpractice2.di.auth.AuthViewModelsModuleJava
import com.example.daggerpractice2.di.auth.AuthModule
import com.example.daggerpractice2.di.auth.AuthScope
import com.example.daggerpractice2.di.auth.AuthViewModelsModule
import com.example.daggerpractice2.di.main.MainFragmentBuildersModule
import com.example.daggerpractice2.di.main.MainModule
import com.example.daggerpractice2.di.main.MainScope
import com.example.daggerpractice2.di.main.MainViewModelsModule
import com.example.daggerpractice2.ui.auth.AuthActivity
import com.example.daggerpractice2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(
       modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMaintActivity(): MainActivity

}