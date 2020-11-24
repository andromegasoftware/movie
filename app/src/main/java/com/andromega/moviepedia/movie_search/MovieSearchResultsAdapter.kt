package com.andromega.moviepedia.movie_search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class MovieSearchResultsAdapter (var movieList: List<MovieSearchDetailModelClass>, val clickListener: (MovieSearchDetailModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<MovieSearchDetailModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_search_result_recyc_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<MovieSearchDetailModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val movieIdmb = itemView.textView_movies_layout_idmb
        val movieYear = itemView.textView_movies_layout_year
        //val cardviewMovies = itemView.cardview_movies_layout

        fun bind(movieSearchDetailModelClass: MovieSearchDetailModelClass, clickListener: (MovieSearchDetailModelClass) -> Unit){
            movieName.text = movieSearchDetailModelClass.title
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + movieSearchDetailModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + movieSearchDetailModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + movieSearchDetailModelClass.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(movieSearchDetailModelClass) }
        }
    }

}