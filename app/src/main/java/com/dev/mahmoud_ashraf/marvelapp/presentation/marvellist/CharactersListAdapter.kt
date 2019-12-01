package com.dev.mahmoud_ashraf.marvelapp.presentation.marvellist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.databinding.MarvelItemBinding

/**
 * Created by dev.mahmoud_ashraf on 11/29/2019.
 */



class CharactersListAdapter : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    private var items: MutableList<CharactersResponse.Result> = mutableListOf()
    var onItemClick: ((pos : Int,data : CharactersResponse.Result) -> Unit)? = null
    private val loading = 0
    private val item = 1

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

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: MutableList<CharactersResponse.Result>) {
            val adapter = adapter as CharactersListAdapter
            adapter.update(items)
        }
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
        private val binding: MarvelItemBinding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.marvel_item,
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

            // Index 1 is the progress bar. Show it while we're loading the image.
            // binding.animatorSecond.setDisplayedChild(1)
            //    binding.animatorFirst.setDisplayedChild(1)


           // Timber.e(""+ Constants.baseUrl+item.secondImage)


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
        ViewHolder(view) {

        init {}
    }
}