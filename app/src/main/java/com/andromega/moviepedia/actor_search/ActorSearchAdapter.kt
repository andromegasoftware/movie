package com.andromega.moviepedia.actor_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class ActorSearchAdapter (var movieList: List<ActorSearchModelClass>, val clickListener: (ActorSearchModelClass) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<ActorSearchModelClass> = ArrayList()

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

    fun submitList(movieList: List<ActorSearchModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieImage = itemView.movies_layout_imageview
        val movieName = itemView.textView_movies_layout_movie_name
        val job = itemView.textView_movies_layout_idmb
        var popularityNumber= itemView.textView_movies_layout_year

        fun bind(actorSearchModelClass: ActorSearchModelClass, clickListener: (ActorSearchModelClass) -> Unit){
            movieName.text = actorSearchModelClass.title
            job.text = actorSearchModelClass.job
            popularityNumber.text = "Popularity: " + actorSearchModelClass.popularity.toString()
            var pictureUrl = "https://image.tmdb.org/t/p/w500" + actorSearchModelClass.posterPath
            Picasso.get().load(pictureUrl).into(movieImage)
            //Log.e("pictureUrl", pictureUrl)

            itemView.setOnClickListener { clickListener(actorSearchModelClass) }
        }
    }

}