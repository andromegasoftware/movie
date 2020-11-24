package com.andromega.moviepedia.cast_known_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.comedy.ComedyMoviesModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_known_movies_recycler_layout.view.*
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class CastKnownMoviesAdapter (var movieList: List<Cast>, val clickListener: (Cast) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<Cast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.cast_known_movies_recycler_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        if(movies.size >= 10){return 10}
        else{return movies.size}
    }

    fun submitList(movieList: List<Cast>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.cast_movies_layout_imageview
        val movieName = itemView.textView_cast_movie_name

        fun bind(cast: Cast, clickListener: (Cast) -> Unit){
            movieName.text = cast.originalTitle
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + cast.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(cast) }
        }
    }
}