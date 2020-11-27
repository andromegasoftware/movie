package com.andromega.moviepedia.popular_tv_series

import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesListModelClass
import retrofit2.Call
import retrofit2.http.GET

interface PopularTvSeriesApi {

    @GET("tv/popular?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")

    fun getPopularTvSeries() : Call<PopularTvSeriesListModelClass>
}