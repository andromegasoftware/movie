package com.andromega.moviepedia.movie_reviews


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultReviewDetailModelClass(
    val author: String,
val content: String,
val id: String,
val url: String
):Serializable