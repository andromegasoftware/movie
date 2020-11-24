package com.andromega.moviepedia.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.circular_recyclerview_layout.view.*
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class NewComingMoviesAdapter(var movieList: List<NewComingMoviesModelClassNew>, val clickListener: (NewComingMoviesModelClassNew) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<NewComingMoviesModelClassNew> = ArrayList()

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

    fun submitList(movieList: List<NewComingMoviesModelClassNew>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val movieIdmb = itemView.textView_movies_layout_idmb
        val movieYear = itemView.textView_movies_layout_year
        val cardviewMovies = itemView.cardview_movies_layout

        fun bind(newComingMoviesModelClass: NewComingMoviesModelClassNew, clickListener: (NewComingMoviesModelClassNew) -> Unit){
            movieName.text = newComingMoviesModelClass.title
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + newComingMoviesModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + newComingMoviesModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + newComingMoviesModelClass.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(newComingMoviesModelClass) }
        }
    }
}