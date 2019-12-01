package com.dev.mahmoud_ashraf.marvelapp.presentation.marvellist

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.Page
import com.dev.mahmoud_ashraf.marvelapp.databinding.MarvelListFragmentBinding
import com.dev.mahmoud_ashraf.marvelapp.utils.*
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class MarvelListFragment : Fragment() {

    private lateinit var binding: MarvelListFragmentBinding
    private lateinit var viewModel: MarvelListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.marvel_list_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this,viewModelFactory)[MarvelListViewModel::class.java]
        viewModel.loading = false
        val layoutManager = LinearLayoutManager(context)

        binding.rvCharacters.layoutManager = layoutManager
        binding.rvCharacters.hasFixedSize()
        binding.viewModelData = viewModel
        // navigate to Details Fragment
        binding.rvCharacters.adapter = CharactersListAdapter().apply {
            onItemClick = { _,data->

                val bundle = Bundle()
                bundle.putParcelable("details",data)
                findNavController().navigate(
                    R.id.action_homeFragment_to_detailsFragment,
                    bundle
                )
            }
        }


        // this extension to handle load more data in RecyclerView
        binding.rvCharacters.endless {
            viewModel.current_page+=Constants.itemsCount
            viewModel.fetchItems()
        }

        // update recycler view when new items inserted
        viewModel.addItems.observe(this, Observer {data ->
            (binding.rvCharacters.adapter as CharactersListAdapter).notifyItemRangeInserted(
                        data.positionStart,
                        data.count
                    ) })

        // update recycler view when items removed
        viewModel.removeItem.observe(this, Observer {data ->
            Observer<Event<Int>> {
                (it).getContentIfNotHandled()?.let {
                    (binding.rvCharacters.adapter as CharactersListAdapter).notifyItemRemoved(it)
                }}
        })

        // handle The State of our Data
        viewModel.stateLiveData.observe(this,
            Observer<Event<NetworkState>> {
                (it).getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    when (it.status) {
                        Status.RUNNING -> {
                            viewModel.showLoading()
                        }
                        Status.SUCCESS -> {
                            if ( viewModel.list.size>0) {
                                viewModel.removeLoading( viewModel.list.size - 1)
                            }
                            else{}

                        }
                        Status.FAILED -> {
                            if ( viewModel.list.size>0) {
                                viewModel.removeLoading( viewModel.list.size - 1)
                            }
                            context?.toast("" + it.message)
                        }

                    }
                }  })

        // handle The coming Data
        viewModel.charactersLiveData.observe(this,
            Observer<Any> {
                (it as Event<*>).getContentIfNotHandled()?.let {
                    // Only proceed if the event has never been handled
                    val data = it as CharactersResponse
                    Timber.e(""+data.data?.results?.size.toString())
                    var newItems = data.data?.results
                    if (newItems==null) newItems = mutableListOf()
                    viewModel.list.addAll(newItems)
                    viewModel.addItems(viewModel.list.size, newItems.size)
                    viewModel.loading = false
                }
            }
        )


        // navigate to Search Fragment
        binding.ivSearch.setOnClickListener {
        val bundle = Bundle()
        bundle.putParcelableArrayList("details",ArrayList<Parcelable>(viewModel.list))
        findNavController().navigate(
            R.id.action_marvelListFragment_to_searchFragment,
            bundle
        )
    }
    }


}
