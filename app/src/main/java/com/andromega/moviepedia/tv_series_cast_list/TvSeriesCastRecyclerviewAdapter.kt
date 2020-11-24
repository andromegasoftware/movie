package com.andromega.moviepedia.tv_series_cast_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.movie_cast.MovieCastModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_recyclerview_layout.view.*

class TvSeriesCastRecyclerviewAdapter (var movieList: List<TvCastModelClass>, val clickListener: (TvCastModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<TvCastModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.cast_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies[position], clickListener)

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<TvCastModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val castImage = itemView.cast_imageview
        val castName = itemView.textViewCastName
        val castRealName = itemView.textViewCastRealName

        fun bind(tvCastModelClass: TvCastModelClass, clickListener: (TvCastModelClass) -> Unit){
            castName.text = tvCastModelClass.castName
            castRealName.text = tvCastModelClass.originalName
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + tvCastModelClass.pictureLink
            Picasso.get().load(pictureUrl).into(castImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(tvCastModelClass) }
        }
    }
}