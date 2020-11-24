package com.andromega.moviepedia.cast_known_movies


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val MovieId: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_count")
    val voteCount: Int
)