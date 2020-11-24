package com.andromega.moviepedia.crime_movies

import com.google.gson.annotations.SerializedName

class CrimeMoviesModelClass(
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