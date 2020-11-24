package com.andromega.moviepedia.tv_series_reviews

import com.andromega.moviepedia.movie_reviews.ResultReviewDetailModelClass
import com.google.gson.annotations.SerializedName

data class TvReviewsModelClass(
    @SerializedName("id")
    val movieId: Int,
    val results: List<TvSeriesResultReviewModelClass>,
    @SerializedName("total_results")
    val totalReviews: Int
)
