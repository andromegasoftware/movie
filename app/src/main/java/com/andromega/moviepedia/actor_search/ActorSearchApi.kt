package com.andromega.moviepedia.actor_search

import com.andromega.moviepedia.movie_search.MovieSearchResultsListModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorSearchApi {

    @GET("search/person?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&query=&page=1&include_adult=false")

    fun getPeople(@Query("query") queryWord: String): Call<ActorSearchListModelClass>

}