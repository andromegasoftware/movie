package com.andromega.moviepedia.kid_popular_movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.kid_recyclerview_single_layout.view.*
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class KidPopularMoviesAdapter (var movieList: List<KidPopularMoviesModelClass>, val clickListener: (KidPopularMoviesModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<KidPopularMoviesModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.kid_recyclerview_single_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<KidPopularMoviesModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview_kid
        val movieName = itemView.textView_movies_layout_movie_name_kid
        val movieIdmb = itemView.textView_movies_layout_idmb_kid
        val movieYear = itemView.textView_movies_layout_year_kid
        //val cardviewMovies = itemView.cardview_movies_layout

        fun bind(kidPopularMoviesModelClass: KidPopularMoviesModelClass, clickListener: (KidPopularMoviesModelClass) -> Unit){
            movieName.text = kidPopularMoviesModelClass.title
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + kidPopularMoviesModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + kidPopularMoviesModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + kidPopularMoviesModelClass.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(kidPopularMoviesModelClass) }
        }
    }
}