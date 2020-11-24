package com.andromega.moviepedia.tv_series_trailer


import com.google.gson.annotations.SerializedName

data class ResultTvYouTubeModelClass(
    val id: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("key")
    val tvSeriesYouTubeKey: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)