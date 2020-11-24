package com.andromega.moviepedia.tv_series_reviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.movie_reviews.MovieReviewsAdapter
import com.andromega.moviepedia.movie_reviews.ResultReviewDetailModelClass
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_comment.reviewsRecyclerview
import kotlinx.android.synthetic.main.activity_tv_reviews.*

class TvReviewsActivity : AppCompatActivity() {

    private var reviewsTvDetailsList:List<TvSeriesResultReviewModelClass> = ArrayList()
    private var tvReviewsDetailList: List<TvSeriesResultReviewModelClass> = ArrayList()
    private lateinit var tvSeriesReviewAdapter: TvSeriesReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_reviews)

        //this is for actionbar and image view using instead of title
        val actionBar = supportActionBar
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        reviewsTvDetailsList = intent.getSerializableExtra("tvRevies") as List<TvSeriesResultReviewModelClass>

        tvReviewsDetailList = reviewsTvDetailsList
        tvSeriesReviewAdapter = TvSeriesReviewAdapter(tvReviewsDetailList)
        tvSeriesReviewAdapter.submitList(tvReviewsDetailList)

        //reviews list recyclerview
        val reviewsListlayoutManager = LinearLayoutManager(this)
        reviewsListlayoutManager.orientation = LinearLayoutManager.VERTICAL
        tvReviewsRecyclerview.layoutManager = reviewsListlayoutManager
        tvReviewsRecyclerview.adapter = tvSeriesReviewAdapter
        tvSeriesReviewAdapter.notifyDataSetChanged()
    }
}