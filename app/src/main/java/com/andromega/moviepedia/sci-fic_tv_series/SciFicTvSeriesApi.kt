package com.andromega.moviepedia.`sci-fic_tv_series`

import com.andromega.moviepedia.action_tv_series.ActionTvSeriesListModelClass
import retrofit2.Call
import retrofit2.http.GET

interface SciFicTvSeriesApi {

    @GET("discover/tv?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&with_genres=10765&include_null_first_air_dates=false")

    fun getSciFicTvSeries() : Call<SciFicTvSeriesListModelClass>
}