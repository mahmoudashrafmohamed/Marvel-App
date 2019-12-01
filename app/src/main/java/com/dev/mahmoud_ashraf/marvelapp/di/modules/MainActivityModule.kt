package com.dev.mahmoud_ashraf.marvelapp.di.modules

import com.dev.mahmoud_ashraf.marvelapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [MainFragmentsModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}