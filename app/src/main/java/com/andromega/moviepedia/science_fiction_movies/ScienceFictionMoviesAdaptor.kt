package com.andromega.moviepedia.science_fiction_movies

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class ScienceFictionMoviesAdaptor (var movieList: List<ScienceFictionMoviesModelClass>, val clickListener: (ScienceFictionMoviesModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<ScienceFictionMoviesModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<ScienceFictionMoviesModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val movieIdmb = itemView.textView_movies_layout_idmb
        val movieYear = itemView.textView_movies_layout_year
        //val cardviewMovies = itemView.cardview_movies_layout

        fun bind(scienceFictionMoviesModelClass: ScienceFictionMoviesModelClass, clickListener: (ScienceFictionMoviesModelClass) -> Unit){

            movieName.text = scienceFictionMoviesModelClass.title
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + scienceFictionMoviesModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + scienceFictionMoviesModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + scienceFictionMoviesModelClass.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(scienceFictionMoviesModelClass) }
        }
    }
}