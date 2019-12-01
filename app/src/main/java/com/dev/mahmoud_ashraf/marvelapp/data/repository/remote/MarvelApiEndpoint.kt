package com.dev.mahmoud_ashraf.marvelapp.data.repository.remote

import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersDetailsResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */
interface MarvelApiEndpoint {

    @GET(Constants.CHARACTERS_SERVICE)
    fun getCharacters(
        @Query(Constants.LIMIT) limit: Int,
        @Query(Constants.OFFSET) pageIndex: Int
    ): Single<CharactersResponse>


    @GET
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=utf-8")
    fun getDetailItemListing(@Url url: String): Single<CharactersDetailsResponse>
}