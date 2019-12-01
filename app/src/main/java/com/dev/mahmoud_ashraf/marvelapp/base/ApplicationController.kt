package com.dev.mahmoud_ashraf.marvelapp.base

import android.app.Activity
import android.app.Application
import com.dev.mahmoud_ashraf.marvelapp.BuildConfig
import com.dev.mahmoud_ashraf.marvelapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
class ApplicationController : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

}