package com.andromega.moviepedia.similar_tv_series

import com.google.gson.annotations.SerializedName

data class ResultTvModelClass(
    @SerializedName("id")
    val tvSeriesId: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)