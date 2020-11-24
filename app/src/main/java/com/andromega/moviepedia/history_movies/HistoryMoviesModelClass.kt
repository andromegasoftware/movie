package com.andromega.moviepedia.history_movies

import com.google.gson.annotations.SerializedName

class HistoryMoviesModelClass(
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