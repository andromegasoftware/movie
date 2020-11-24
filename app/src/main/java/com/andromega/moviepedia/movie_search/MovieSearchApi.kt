package com.andromega.moviepedia.movie_search

import com.andromega.moviepedia.movie_reviews.MovieReviewsModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieSearchApi {

    @GET("search/multi?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&query=&page=1&include_adult=false")

    fun getMoviesAndSeriesAndPeople(@Query("query") queryWord: String): Call<MovieSearchResultsListModelClass>
}