package com.andromega.moviepedia.tv_series_search

import com.google.gson.annotations.SerializedName

class TvSeriesSearchModelClass(

    @SerializedName("id")
    val movieId: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("first_air_date")
    val releaseDate: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
) {
}