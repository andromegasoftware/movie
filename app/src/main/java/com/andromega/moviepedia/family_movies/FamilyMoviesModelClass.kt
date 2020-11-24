package com.andromega.moviepedia.family_movies

import com.google.gson.annotations.SerializedName

class FamilyMoviesModelClass(
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