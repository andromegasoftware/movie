package com.andromega.moviepedia.movie_reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.tv_series_reviews.TvSeriesResultReviewModelClass
import kotlinx.android.synthetic.main.activity_comment.*

class CommentActivity : AppCompatActivity() {

    var reviewsDetailsList:List<ResultReviewDetailModelClass> = ArrayList()
    private lateinit var movieReviewsAdapter: MovieReviewsAdapter
    private var movies: List<ResultReviewDetailModelClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        //this is for actionbar and image view using instead of title
        val actionBar = supportActionBar
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        reviewsDetailsList = intent.getSerializableExtra("movieRevies") as List<ResultReviewDetailModelClass>

        movies = reviewsDetailsList
        movieReviewsAdapter = MovieReviewsAdapter(movies)
        movieReviewsAdapter.submitList(movies)

        //reviews list recyclerview
        val reviewsListlayoutManager = LinearLayoutManager(this)
        reviewsListlayoutManager.orientation = LinearLayoutManager.VERTICAL
        reviewsRecyclerview.layoutManager = reviewsListlayoutManager
        reviewsRecyclerview.adapter = movieReviewsAdapter
        movieReviewsAdapter.notifyDataSetChanged()



        //Log.e("revies: ", movieReviewsAdapter.movieList.toString())

    }
}