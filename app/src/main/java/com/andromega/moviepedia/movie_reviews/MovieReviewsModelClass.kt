package com.andromega.moviepedia.movie_reviews


import com.google.gson.annotations.SerializedName

data class MovieReviewsModelClass(
    @SerializedName("id")
    val movieId: Int,
    val results: List<ResultReviewDetailModelClass>,
    @SerializedName("total_results")
    val totalReviews: Int
)