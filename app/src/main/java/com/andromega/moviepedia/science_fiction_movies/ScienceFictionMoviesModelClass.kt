package com.andromega.moviepedia.science_fiction_movies

import com.google.gson.annotations.SerializedName

class ScienceFictionMoviesModelClass(
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,
) {
}