package com.andromega.moviepedia.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.circular_recyclerview_layout.view.*

class CirculerImageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<MovieModelClass> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.circular_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(movies.get(position))

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<MovieModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.circuler_imageview
        val movieName = itemView.textViewCircularMovieName

        fun bind(movieModelClass: MovieModelClass){
            movieName.text = movieModelClass.MovieName
            var pictureUrl = movieModelClass.picture
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)
        }
    }



}