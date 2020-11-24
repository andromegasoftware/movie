package com.andromega.moviepedia.tv_series_info


import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("air_date")
    val airDate: Any,
    @SerializedName("episode_count")
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: Any,
    @SerializedName("season_number")
    val seasonNumber: Int
)