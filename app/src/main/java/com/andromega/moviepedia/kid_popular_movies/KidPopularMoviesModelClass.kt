package com.andromega.moviepedia.kid_popular_movies

import com.google.gson.annotations.SerializedName

class KidPopularMoviesModelClass(
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("backdrop_path")
    var backdropPath:String
)  {
}