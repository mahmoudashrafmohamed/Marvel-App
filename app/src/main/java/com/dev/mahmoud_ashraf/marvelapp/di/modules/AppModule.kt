package com.dev.mahmoud_ashraf.marvelapp.di.modules

import com.dev.mahmoud_ashraf.marvelapp.data.repository.remote.MarvelApiEndpoint
import com.dev.mahmoud_ashraf.marvelapp.utils.Constants
import com.dev.mahmoud_ashraf.marvelapp.utils.HashUtils
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    internal fun provideApi(okHttpClient: OkHttpClient): MarvelApiEndpoint {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MarvelApiEndpoint::class.java)
    }

   /* @Singleton
    @Provides
    internal fun provideDb(app: Application): RepoDatabase {
        return Room.databaseBuilder( app,
            RepoDatabase::class.java, "github.db")
            .build()
    }*/
   @Provides
   @Singleton
   fun loggingInterceptor(): HttpLoggingInterceptor {
       val loggingInterceptor = HttpLoggingInterceptor()
       loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
       return loggingInterceptor
   }



    @Singleton
    @Provides
    internal fun provideOkHttpClient( logging: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder()
                    // change with your api data
                    .addQueryParameter(Constants.timeStamp,  Constants.TS )
                    .addQueryParameter(Constants.apiKey, Constants.API_KEY)
                    .addQueryParameter(Constants.hashKey, HashUtils.getHash(Constants.TS, Constants.PRIVATE_KEY, Constants.API_KEY))
                    .build()

                val newRequest = request.newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
            .addInterceptor(logging)
            .build()
    }


}