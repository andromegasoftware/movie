package com.andromega.moviepedia.movie_cast

import com.google.gson.annotations.SerializedName

class MovieCastModelClass(
    @SerializedName("id")
    val castId: Int,

    @SerializedName("character")
    val castName: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("profile_path")
    val pictureLink: String,) {
}