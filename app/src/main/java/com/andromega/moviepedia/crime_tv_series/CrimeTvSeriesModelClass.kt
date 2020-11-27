package com.andromega.moviepedia.crime_tv_series

import com.google.gson.annotations.SerializedName

class CrimeTvSeriesModelClass(
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("first_air_date")
    val releaseDate: String,
    var id: Int,
    @SerializedName("name")
    var MovieName: String,
    @SerializedName("poster_path")
    var picture: String,
) {
}