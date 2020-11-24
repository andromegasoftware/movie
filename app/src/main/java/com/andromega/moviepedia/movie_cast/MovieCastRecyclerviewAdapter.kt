package com.andromega.moviepedia.movie_cast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_recyclerview_layout.view.*
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class MovieCastRecyclerviewAdapter (var movieList: List<MovieCastModelClass>, val clickListener: (MovieCastModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<MovieCastModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.cast_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<MovieCastModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val castImage = itemView.cast_imageview
        val castName = itemView.textViewCastName
        val castRealName = itemView.textViewCastRealName

        fun bind(movieCastModelClass: MovieCastModelClass, clickListener: (MovieCastModelClass) -> Unit){
            castName.text = movieCastModelClass.castName
            castRealName.text = movieCastModelClass.originalName
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + movieCastModelClass.pictureLink
            Picasso.get().load(pictureUrl).into(castImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(movieCastModelClass) }
        }
    }
}