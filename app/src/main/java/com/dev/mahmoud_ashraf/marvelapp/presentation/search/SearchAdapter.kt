package com.dev.mahmoud_ashraf.marvelapp.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.databinding.MarvelItemBinding
import com.dev.mahmoud_ashraf.marvelapp.databinding.SearchItemBinding

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */



class SearchAdapter(  var items: MutableList<CharactersResponse.Result> = mutableListOf()) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() , Filterable {


    var filter: SearchCustomFilter? = null
    var onItemClick: ((pos : Int,data : CharactersResponse.Result) -> Unit)? = null
    private val loading = 0
    private val item = 1

    override fun getFilter(): Filter {
        if (filter == null) {
            filter = SearchCustomFilter(items , this)
        }
        return filter!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == loading) {
            LoadingViewHolder(parent)
        } else {
            ItemViewHolder(parent)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemViewHolder && items.size > position) {
            holder.bind(items[position])
        }
    }

    fun update(items: MutableList<CharactersResponse.Result>) {
        this.items = items
        notifyDataSetChanged()
    }



    override fun getItemViewType(position: Int) =
        if (items[position].name == "loading") loading else item

    fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: SearchItemBinding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_item,
            parent,
            false
        )
    ) : ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(adapterPosition, items[adapterPosition])
            }
        }

        fun bind(item: CharactersResponse.Result) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    class LoadingViewHolder(
        private val parent: ViewGroup,
        view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.loading_view,
            parent,
            false
        )
    ) :
        ViewHolder(view)
}