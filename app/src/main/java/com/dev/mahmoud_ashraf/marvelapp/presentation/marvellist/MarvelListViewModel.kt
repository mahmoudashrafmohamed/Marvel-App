package com.dev.mahmoud_ashraf.marvelapp.presentation.marvellist

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.mahmoud_ashraf.marvelapp.data.model.Page
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.domain.usecase.CharactersUseCase
import com.dev.mahmoud_ashraf.marvelapp.utils.Constants
import com.dev.mahmoud_ashraf.marvelapp.utils.Event
import com.dev.mahmoud_ashraf.marvelapp.utils.NetworkState
import javax.inject.Inject

class MarvelListViewModel @Inject constructor(application: Application, private val useCase: CharactersUseCase)
    :  AndroidViewModel(application)  {

    var current_page = 0
    val list = mutableListOf<CharactersResponse.Result?>()

    private val _items = MutableLiveData<List<CharactersResponse.Result?>>().apply { value = list }
    private val _addItems = MutableLiveData<Page>()
    private val _removeItem = MutableLiveData<Event<Int>>()
    private val handler = Handler()
    var loading = false
    private var delay = 0L

    val items: LiveData<List<CharactersResponse.Result?>>
        get() = _items

    // observe it to refresh recycler when new items inserted
    val addItems: LiveData<Page>
        get() = _addItems

    // observe it to refresh recycler when items removed
    val removeItem: LiveData<Event<Int>>
        get() = _removeItem

    // observe it to handle the state of data
    var stateLiveData  : LiveData<Event<NetworkState>> = useCase.validateStateLiveData()

    // observe it to handle the data itself
    var charactersLiveData : LiveData<Event<Any>> =useCase.validateMarvelResponseLiveData()
    init {
        fetchItems()
    }


    fun fetchItems() {
        if (!loading) {
            loading = true
            handler.postDelayed({
                useCase.validateCharacters(Constants.itemsCount,current_page)
            }, delay)
        }
    }

    fun showLoading() {
        list.add(CharactersResponse.Result().apply { name= "loading"})
        addItems(list.size - 1, 1)
    }

    fun removeLoading(position: Int) {
        list.removeAt(position)
        removeItems(position)
    }

    fun addItems(positionStart: Int, count: Int) {
        _addItems.value = Page(positionStart, count)
    }

    private fun removeItems(positionStart: Int) {
        _removeItem.value = Event(positionStart)
    }


    private fun cleanObservables() = useCase.clear()
    override fun onCleared() {
        super.onCleared()
        cleanObservables()
    }
}