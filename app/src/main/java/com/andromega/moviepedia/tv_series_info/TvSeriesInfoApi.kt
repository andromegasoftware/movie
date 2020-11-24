package com.andromega.moviepedia.tv_series_info

import com.andromega.moviepedia.data.vo.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvSeriesInfoApi {

    @GET("tv/{tvSeriesId}?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")


    fun fetchAllTvData(@Path("tvSeriesId") tvSeriesId: String): Call<TvSeriesInfoModelClass>
}