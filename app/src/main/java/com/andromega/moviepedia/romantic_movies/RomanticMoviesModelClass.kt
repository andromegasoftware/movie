package com.andromega.moviepedia.romantic_movies

import com.google.gson.annotations.SerializedName

class RomanticMoviesModelClass(
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,
)  {
}