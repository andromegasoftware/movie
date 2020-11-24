package com.andromega.moviepedia.movie_search


import com.google.gson.annotations.SerializedName

data class MovieSearchResultsListModelClass(
    val page: Int,
    val results: List<MovieSearchDetailModelClass>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)