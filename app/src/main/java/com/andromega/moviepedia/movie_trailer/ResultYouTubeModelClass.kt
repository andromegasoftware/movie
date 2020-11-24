package com.andromega.moviepedia.movie_trailer


import com.google.gson.annotations.SerializedName

data class ResultYouTubeModelClass(
    @SerializedName("key")
    val MovieYouTubeKey: String,
)