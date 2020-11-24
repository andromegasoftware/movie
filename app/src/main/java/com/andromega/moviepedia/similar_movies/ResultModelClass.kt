package com.andromega.moviepedia.similar_movies


import com.google.gson.annotations.SerializedName

data class ResultModelClass(
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)