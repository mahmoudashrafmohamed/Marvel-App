package com.dev.mahmoud_ashraf.marvelapp.di

import android.app.Application
import com.dev.mahmoud_ashraf.marvelapp.base.ApplicationController
import com.dev.mahmoud_ashraf.marvelapp.di.components.DaggerAppComponent

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

object AppInjector {
    fun init(marvelApp: ApplicationController) {
        DaggerAppComponent.builder().application(marvelApp as Application).build().inject(marvelApp)
    }
}