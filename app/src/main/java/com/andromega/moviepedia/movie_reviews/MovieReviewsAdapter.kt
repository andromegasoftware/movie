package com.andromega.moviepedia.movie_reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.andromega.moviepedia.movies.CirculerImageAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_recycler_layout.view.*
import kotlinx.android.synthetic.main.movie_recyclerview_layout.view.*

class MovieReviewsAdapter (var movieList: List<ResultReviewDetailModelClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<ResultReviewDetailModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val my_view = LayoutInflater.from(parent.context).inflate(R.layout.comment_recycler_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as MovieReviewsAdapter.MoviesViewHolder).bind(movies[position])

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<ResultReviewDetailModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieName = itemView.textViewReviewAuthorName
        val movieReview = itemView.textViewReviewDetail

        fun bind(resultReviewDetailModelClass: ResultReviewDetailModelClass){
            movieName.text = resultReviewDetailModelClass.author
            movieReview.text = resultReviewDetailModelClass.content

        }
    }
}