package com.andromega.moviepedia.western_tv_series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class WesternTvSeriesAdapter (var movieList: List<WesternTvSeriesModelClass>, val clickListener: (WesternTvSeriesModelClass) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var circularMovies: List<WesternTvSeriesModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.movie_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(circularMovies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return circularMovies.size

    }

    fun submitList(circularMovieList: List<WesternTvSeriesModelClass>){
        circularMovies = circularMovieList
    }


    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val movieIdmb = itemView.textView_movies_layout_idmb
        val movieYear = itemView.textView_movies_layout_year

        fun bind(westernTvSeriesModelClass: WesternTvSeriesModelClass, clickListener: (WesternTvSeriesModelClass) -> Unit){
            movieName.text = westernTvSeriesModelClass.MovieName
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + westernTvSeriesModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + westernTvSeriesModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + westernTvSeriesModelClass.picture
            Picasso.get().load(pictureUrl).error(R.drawable.avenger).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(westernTvSeriesModelClass) }
        }
    }
}