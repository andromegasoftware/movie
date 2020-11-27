package com.andromega.moviepedia.documentary_tv_series

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class DocumentaryTvSeriesAdapter(var movieList: List<DocumentaryTvSeriesModelClass>, val clickListener: (DocumentaryTvSeriesModelClass) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var circularMovies: List<DocumentaryTvSeriesModelClass> = ArrayList()

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

    fun submitList(circularMovieList: List<DocumentaryTvSeriesModelClass>){
        circularMovies = circularMovieList
    }


    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val movieIdmb = itemView.textView_movies_layout_idmb
        val movieYear = itemView.textView_movies_layout_year

        fun bind(documentaryTvSeriesModelClass: DocumentaryTvSeriesModelClass, clickListener: (DocumentaryTvSeriesModelClass) -> Unit){
            movieName.text = documentaryTvSeriesModelClass.MovieName
            movieIdmb.text = itemView.resources.getString(R.string.average_vote) + documentaryTvSeriesModelClass.voteAverage
            movieYear.text = itemView.resources.getString(R.string.main_page_year) + documentaryTvSeriesModelClass.releaseDate
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + documentaryTvSeriesModelClass.picture
            Picasso.get().load(pictureUrl).error(R.drawable.avenger).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(documentaryTvSeriesModelClass) }
        }
    }
}