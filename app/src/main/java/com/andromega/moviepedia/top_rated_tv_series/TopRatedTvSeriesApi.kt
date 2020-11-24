package com.andromega.moviepedia.top_rated_tv_series

import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesMovieList
import retrofit2.Call
import retrofit2.http.GET

interface TopRatedTvSeriesApi {

    @GET("tv/top_rated?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")

    fun getTopRatedTvSeries() : Call<TopRatedTvSeriesListModelClass>
}