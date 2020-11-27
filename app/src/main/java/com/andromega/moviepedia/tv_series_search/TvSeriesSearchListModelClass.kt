package com.andromega.moviepedia.tv_series_search

import com.andromega.moviepedia.movie_search.MovieSearchDetailModelClass
import com.google.gson.annotations.SerializedName

class TvSeriesSearchListModelClass(
    val page: Int,
    val results: List<TvSeriesSearchModelClass>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
}