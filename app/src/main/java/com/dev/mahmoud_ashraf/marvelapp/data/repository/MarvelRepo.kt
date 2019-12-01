package com.dev.mahmoud_ashraf.marvelapp.data.repository

import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersDetailsResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.data.repository.remote.MarvelApiEndpoint
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
class MarvelRepo @Inject
constructor(private val remote: MarvelApiEndpoint) {
    fun getCharacters(limit: Int, pageIndex: Int): Flowable<CharactersResponse> {
        val remoteData =
            remote.getCharacters( limit, pageIndex)
        return remoteData.toFlowable()
    }


    fun getDetailItemListing(url : String): Flowable<CharactersDetailsResponse> {
        val remoteData =
            remote.getDetailItemListing( url )
        return remoteData.toFlowable()
    }
}