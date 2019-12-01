package com.dev.mahmoud_ashraf.marvelapp.presentation.details

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide

import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersDetailsResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.movie
import com.dev.mahmoud_ashraf.marvelapp.utils.Event
import com.dev.mahmoud_ashraf.marvelapp.utils.NetworkState
import com.dev.mahmoud_ashraf.marvelapp.utils.Status
import com.dev.mahmoud_ashraf.marvelapp.utils.toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.details_fragment.*
import timber.log.Timber
import javax.inject.Inject

class DetailsFragment : Fragment() {

    var details:CharactersResponse.Result?= null
    private var categoriesList = ArrayList<String>()
    private lateinit var viewModel: DetailsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var moviesListAdapter : CategoriesAdapter ?= null



    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailsViewModel::class.java)
         details  = arguments?.getParcelable<CharactersResponse.Result>("details")

        initCategoriesList()
        setImage(details)

        ivBack.setOnClickListener {
            findNavController().navigateUp()
        }



    }

    private fun setImage(characterData: CharactersResponse.Result?){
        val avatarUrl = characterData?.thumbnail?.path

        Timber.e(""+characterData?.id)
        if (avatarUrl != null && avatarUrl.isNotEmpty())
            Glide.with(this).load(avatarUrl.plus("/portrait_xlarge.jpg")).into(ivMovieImage)

        tvCharacterName.text = characterData?.name
        tvDescription.text = characterData?.description
    }

    private fun initCategoriesList() {


        categoriesList.add(getString(R.string.comics))
        categoriesList.add(getString(R.string.series))
        categoriesList.add(getString(R.string.stories))
        categoriesList.add(getString(R.string.events))

        rvCategories.layoutManager = LinearLayoutManager(context)
        rvCategories.setHasFixedSize(true)


        moviesListAdapter = CategoriesAdapter(categoriesList,  activity!!)
        if (rvCategories.adapter==null)
            rvCategories.adapter = moviesListAdapter

        if (details?.comics!=null)
        viewModel.fetchItems(""+details?.resourceURI+ "/comics",0)

        if (details?.series!=null)
            viewModel.fetchItems(""+details?.resourceURI+ "/series",1)

        if (details?.stories!=null)
            viewModel.fetchItems(""+details?.resourceURI+ "/stories",2)

        if (details?.events!=null)
            viewModel.fetchItems(""+details?.resourceURI+ "/events",3)

        viewModel.stateLiveData.observe(this,
            Observer<Event<NetworkState>> {
                (it).getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    when (it.status) {
                        Status.RUNNING -> {
                        }
                        Status.SUCCESS -> {
                        }
                        Status.FAILED -> {
                            context?.toast("" + it.message)
                        }}
                }  })

        viewModel.charactersLiveData.observe(this,
            Observer<Any> {
                (it as Event<*>).getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    val pair = it as Pair<*, *>
                    val data = pair.first as CharactersDetailsResponse
                    val type = pair.second
                    Timber.e(""+data.toString())
                    Timber.e(""+data.data?.results.toString())

                    val movies = mutableListOf<movie?>()
                   val resultLists = data.data?.results
                    resultLists?.map {
                        movies.add(movie(""+it?.title ,""+it?.thumbnail?.path ) )
                    }

                    if (type==0) {
                        moviesListAdapter?.addComicsList(movies)
                    }
                    else if (type==1){
                        moviesListAdapter?.addSeriesList(movies)
                    }
                    else if(type==2){
                        moviesListAdapter?.addStoriesList(movies)
                    }
                    else{
                        moviesListAdapter?.addEventsList(movies)
                    }

                }
            }
        )
    }

}
