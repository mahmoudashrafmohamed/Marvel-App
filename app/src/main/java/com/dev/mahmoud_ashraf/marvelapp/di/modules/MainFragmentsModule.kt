package com.dev.mahmoud_ashraf.marvelapp.di.modules

import com.dev.mahmoud_ashraf.marvelapp.presentation.details.DetailsFragment
import com.dev.mahmoud_ashraf.marvelapp.presentation.marvellist.MarvelListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
@Module
abstract class MainFragmentsModule {

     @ContributesAndroidInjector
    internal abstract fun contributeMarvelListFragment(): MarvelListFragment
    @ContributesAndroidInjector
    internal abstract fun contributeDetailsFragment(): DetailsFragment
    /*@ContributesAndroidInjector
    internal abstract fun contributeSearchFragment(): SearchFragment
    @ContributesAndroidInjector
    internal abstract fun contributeSourceDetailFragment(): SourceDetailFragment*/

}