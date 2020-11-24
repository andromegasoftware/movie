package com.andromega.moviepedia.movie_search


import com.google.gson.annotations.SerializedName

data class MovieSearchDetailModelClass(

    @SerializedName("id")
    val movieId: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
)