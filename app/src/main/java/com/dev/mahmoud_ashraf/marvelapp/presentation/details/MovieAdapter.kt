package com.dev.mahmoud_ashraf.marvelapp.presentation.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.marvelapp.R
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse
import com.dev.mahmoud_ashraf.marvelapp.data.model.movie

class MovieAdapter(private val movieList:  MutableList<movie?>?, private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList!![position]
        holder.textViewTitle.text = movie?.title

        Glide
            .with(context)
            .load(movie?.img/*resourceURI*/.plus("/portrait_xlarge.jpg"))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(holder.imageViewMovie)

    }

    override fun getItemCount(): Int {
        return movieList?.size?:0
    }

    fun update(results: MutableList<movie>) {
        movieList?.addAll(results)
        notifyDataSetChanged()
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView = itemView.findViewById(R.id.tv_title)
        val imageViewMovie: ImageView = itemView.findViewById(R.id.image_view_movie)
    }
}