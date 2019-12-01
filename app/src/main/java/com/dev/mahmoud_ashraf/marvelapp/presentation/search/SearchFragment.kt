package com.dev.mahmoud_ashraf.marvelapp.presentation.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.databinding.FragmentSearchBinding
import com.jakewharton.rxbinding.widget.RxTextView
import rx.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private var details : MutableList<CharactersResponse.Result>? = null
    private var charactersListAdapter : SearchAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         details  = arguments?.getParcelableArrayList<CharactersResponse.Result>("details")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        initCharactersList()
        observerTextChanges()
    }

    private fun initCharactersList() {
       val layoutManager = LinearLayoutManager(activity)
       binding.rvSearch.layoutManager = layoutManager
        binding.rvSearch.setHasFixedSize(true)
        val dividerItemDecoration = DividerItemDecoration(binding. rvSearch.context, layoutManager.orientation)
        binding.rvSearch.addItemDecoration(dividerItemDecoration)
        charactersListAdapter = SearchAdapter(details!!)
        binding.rvSearch.adapter = charactersListAdapter
    }


    private fun observerTextChanges() {
        RxTextView.textChanges(binding.etSearch!!).debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when {
                    binding.etSearch!!.text.toString().isNotEmpty() -> {
                           binding.rvSearch.visibility = View.VISIBLE
                        charactersListAdapter?.getFilter()?.filter(it)
                    }
                    else -> {
                        charactersListAdapter?.notifyDataSetChanged()
                    }
                }
            }
    }


}
