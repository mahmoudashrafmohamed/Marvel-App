package com.dev.mahmoud_ashraf.marvelapp.presentation.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.movie

const val FIRST_POSITION = 0
const val SECOND_POSITION = 1
const val THIRD_POSITION = 2
const val FOURTH_POSITION = 3

class CategoriesAdapter(
    private val categoriesList: ArrayList<String>,
    private val context: Context
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val comics :  MutableList<movie?>? = mutableListOf()
    private val series :  MutableList<movie?>? = mutableListOf()
    private val stories :  MutableList<movie?>? = mutableListOf()
    private val events :  MutableList<movie?>? = mutableListOf()

    private var horizontalAdapter: MovieAdapter? = null
    private val recycledViewPool: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()



    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_layout_categories,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(@NonNull holder: CategoriesViewHolder, position: Int) {
        when (position) {
            FIRST_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.comics)
                horizontalAdapter = MovieAdapter(comics, context)
            }
            SECOND_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.series)
                horizontalAdapter = MovieAdapter(series, context)
            }
            THIRD_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.stories)
                horizontalAdapter = MovieAdapter(stories , context)
            }
            FOURTH_POSITION -> {
                holder.textViewCategory.text = context.getString(R.string.events)
                horizontalAdapter = MovieAdapter(events , context)
            }
        }

        holder.recyclerViewHorizontal.adapter = horizontalAdapter
        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    fun addComicsList(movies: MutableList<movie?>) {
        comics?.addAll(movies)
        notifyDataSetChanged()
    }

    fun addSeriesList(movies: MutableList<movie?>) {
        series?.addAll(movies)
        notifyDataSetChanged()
    }

    fun addStoriesList(movies: MutableList<movie?>){
        stories?.addAll(movies)
        notifyDataSetChanged()
    }

    fun addEventsList(movies: MutableList<movie?>) {
        events?.addAll(movies)
        notifyDataSetChanged()
    }


    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recyclerViewHorizontal: RecyclerView = itemView.findViewById(R.id.home_recycler_view_horizontal)
        val textViewCategory: TextView

        private val horizontalManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        init {
            recyclerViewHorizontal.setHasFixedSize(true)
            recyclerViewHorizontal.isNestedScrollingEnabled = false
            recyclerViewHorizontal.layoutManager = horizontalManager
            recyclerViewHorizontal.itemAnimator = DefaultItemAnimator()
            textViewCategory = itemView.findViewById(R.id.tv_movie_category)
        }

    }

}
