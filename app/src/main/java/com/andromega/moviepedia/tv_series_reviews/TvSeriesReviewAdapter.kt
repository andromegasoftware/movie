package com.andromega.moviepedia.tv_series_reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andromega.moviepedia.R
import com.andromega.moviepedia.movie_reviews.MovieReviewsAdapter
import com.andromega.moviepedia.movie_reviews.ResultReviewDetailModelClass
import kotlinx.android.synthetic.main.comment_recycler_layout.view.*

class TvSeriesReviewAdapter (var movieList: List<TvSeriesResultReviewModelClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var movies: List<TvSeriesResultReviewModelClass> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val my_view = LayoutInflater.from(parent.context).inflate(R.layout.comment_recycler_layout, parent, false)

        return MoviesViewHolder(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as TvSeriesReviewAdapter.MoviesViewHolder).bind(movies[position])

    }

    override fun getItemCount(): Int {

        return movies.size

    }

    fun submitList(movieList: List<TvSeriesResultReviewModelClass>){
        movies = movieList
    }

    class MoviesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val movieName: TextView = itemView.textViewReviewAuthorName
        private val movieReview: TextView = itemView.textViewReviewDetail

        fun bind(tvSeriesResultReviewModelClass: TvSeriesResultReviewModelClass){
            movieName.text = tvSeriesResultReviewModelClass.author
            movieReview.text = tvSeriesResultReviewModelClass.content

        }
    }
}