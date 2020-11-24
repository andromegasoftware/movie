package com.andromega.moviepedia.top_rated_tv_series


import com.google.gson.annotations.SerializedName

data class TopRatedTvSeriesModelClass(
    var id: Int,
    @SerializedName("name")
    var MovieName: String,
    @SerializedName("poster_path")
    var picture: String,
    @SerializedName("backdrop_path")
    var picturePoster:String
)