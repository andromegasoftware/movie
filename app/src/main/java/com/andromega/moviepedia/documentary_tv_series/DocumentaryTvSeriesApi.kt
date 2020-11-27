package com.andromega.moviepedia.documentary_tv_series

import retrofit2.Call
import retrofit2.http.GET

interface DocumentaryTvSeriesApi {

    @GET("discover/tv?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&with_genres=99&include_null_first_air_dates=false")

    fun getDocumentaryTvSeries() : Call<DocumentaryTvSeriesListModelClass>
}