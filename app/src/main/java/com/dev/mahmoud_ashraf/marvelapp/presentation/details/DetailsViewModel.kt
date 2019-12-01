package com.dev.mahmoud_ashraf.marvelapp.presentation.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.domain.usecase.SubItemsUseCase
import com.dev.mahmoud_ashraf.marvelapp.utils.Event
import com.dev.mahmoud_ashraf.marvelapp.utils.NetworkState
import javax.inject.Inject

class DetailsViewModel @Inject constructor(application: Application, private val useCase: SubItemsUseCase)
    :  AndroidViewModel(application)  {

    var stateLiveData  : LiveData<Event<NetworkState>> = useCase.validateStateLiveData()
    var charactersLiveData : LiveData<Event<Any>> =useCase.validateMarvelResponseLiveData()

    fun fetchItems(url : String,type : Int) {
        useCase.validateCharacters(url,type)
    }

    private fun cleanObservables() = useCase.clear()

    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }
}