package com.andromega.moviepedia.top_rated_tv_series


import com.google.gson.annotations.SerializedName

data class TopRatedTvSeriesListModelClass(
    val page: Int,
    val results: List<TopRatedTvSeriesModelClass>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)