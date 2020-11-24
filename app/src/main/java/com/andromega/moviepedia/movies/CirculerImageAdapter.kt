package com.andromega.moviepedia.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.circular_recyclerview_layout.view.*

class CirculerImageAdapter(var movieList: List<CircularMovieModelClass>, val clickListener: (CircularMovieModelClass) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var circularMovies: List<CircularMovieModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var my_view = LayoutInflater.from(parent.context).inflate(R.layout.circular_recyclerview_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MoviesViewHolder).bind(circularMovies.get(position), clickListener)

    }

    override fun getItemCount(): Int {

        return circularMovies.size

    }

    fun submitList(circularMovieList: List<CircularMovieModelClass>){
        circularMovies = circularMovieList
    }


    class MoviesViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.circuler_imageview
        val movieName = itemView.textViewCircularMovieName

        fun bind(circularMovieModelClass: CircularMovieModelClass, clickListener: (CircularMovieModelClass) -> Unit){
            movieName.text = circularMovieModelClass.MovieName
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + circularMovieModelClass.picture
            Picasso.get().load(pictureUrl).error(R.drawable.avenger).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(circularMovieModelClass) }
        }
    }



}