package com.dev.mahmoud_ashraf.marvelapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.mahmoud_ashraf.marvelapp.presentation.marvellist.MarvelListViewModel
import com.dev.mahmoud_ashraf.marvelapp.base.ViewModelFactory
import com.dev.mahmoud_ashraf.marvelapp.di.ViewModelKey
import com.dev.mahmoud_ashraf.marvelapp.presentation.details.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MarvelListViewModel::class)
    internal abstract fun bindMarvelListViewModel(marvelListViewModel: MarvelListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}