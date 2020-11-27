package com.andromega.moviepedia.actor_search

import com.andromega.moviepedia.movie_search.MovieSearchDetailModelClass
import com.google.gson.annotations.SerializedName

class ActorSearchListModelClass(
    val page: Int,
    val results: List<ActorSearchModelClass>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
}