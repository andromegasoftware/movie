package com.andromega.moviepedia.tv_series_search

import com.andromega.moviepedia.movie_search.MovieSearchResultsListModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSeriesSearchApi {

    @GET("search/tv?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1&query=&include_adult=false")

    fun getTvSeries(@Query("query") queryWord: String): Call<TvSeriesSearchListModelClass>

}