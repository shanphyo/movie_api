package com.mic.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mic.movie.R
import  com.mic.movie.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(var rd:MovieClick) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    var resultList: List<Result> = listOf()

    fun updateResultlist(resultList: List<Result>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindMovies(result: Result) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w200${result.poster_path}")
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.moviePoster)
            itemView.movie_id.setOnClickListener {
                rd.onFunClick(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies,parent,false)
        return  MoviesViewHolder(view)
    }

    override fun getItemCount(): Int =
        resultList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindMovies(resultList[position])
    }
}