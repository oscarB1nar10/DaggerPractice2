package com.example.daggerpractice2.di

import android.app.Application
import com.example.daggerpractice2.BaseApplication
import com.example.daggerpractice2.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
                ActivityBuilderModule::class,
                AppModule::class,
                ViewModelFactoryModule::class]
)
interface AppComponent :  AndroidInjector<BaseApplication>{

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}