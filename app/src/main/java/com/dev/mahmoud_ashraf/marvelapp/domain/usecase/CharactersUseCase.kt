package com.dev.mahmoud_ashraf.marvelapp.domain.usecase

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.repository.MarvelRepo
import com.dev.mahmoud_ashraf.marvelapp.utils.Event
import com.dev.mahmoud_ashraf.marvelapp.utils.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
class CharactersUseCase
@Inject
constructor(private val application: Application, private val marvelRepo : MarvelRepo) {




    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val validateState: MutableLiveData<Event<NetworkState>> = MutableLiveData()
    private val validateMarvelResponseData: MutableLiveData<Event <Any>> = MutableLiveData()
    fun validateStateLiveData(): LiveData<Event<NetworkState>> = validateState
    fun validateMarvelResponseLiveData(): LiveData< Event <Any>> = validateMarvelResponseData
    @SuppressLint("CheckResult")
    fun validateCharacters(limit: Int, pageIndex: Int) {
        if (compositeDisposable.isDisposed)
            compositeDisposable = CompositeDisposable()
        compositeDisposable.add(marvelRepo.getCharacters(limit, pageIndex )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe
            {
                validateState.postValue(Event(NetworkState.LOADING))
            }
            .subscribe(
                { data ->
                    data?.let {
                        validateState.postValue(Event(NetworkState.LOADED))
                        validateMarvelResponseData.postValue( Event (it))
                        Timber.e(""+it)
                    } ?: run {
                        validateState.postValue(Event(NetworkState.error(application.getString(R.string.data_finished))))
                    }
                },
                { throwable ->
                    if (throwable is IOException){
                        validateState.postValue(Event(NetworkState.error(
                            application.getString( R.string.need_internet))))
                    }
                    else
                    {
                        throwable.printStackTrace()
                        validateState.postValue(
                            Event(
                                NetworkState.error(
                                    throwable.message ?:application.getString( R.string.something_wrong))
                            )
                        )
                }}
            )
        )
    }





    fun clear() {
        compositeDisposable.dispose()
    }



}