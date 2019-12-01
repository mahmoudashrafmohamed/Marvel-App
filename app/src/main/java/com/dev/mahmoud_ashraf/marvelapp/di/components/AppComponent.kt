package com.dev.mahmoud_ashraf.marvelapp.di.components

import android.app.Application
import com.dev.mahmoud_ashraf.marvelapp.base.ApplicationController
import com.dev.mahmoud_ashraf.marvelapp.di.modules.AppModule
import com.dev.mahmoud_ashraf.marvelapp.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: ApplicationController)


}