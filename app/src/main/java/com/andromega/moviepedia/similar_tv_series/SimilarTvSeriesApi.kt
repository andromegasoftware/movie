package com.andromega.moviepedia.similar_tv_series

import com.andromega.moviepedia.similar_movies.SimilarMoviesModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimilarTvSeriesApi {

    @GET("tv/{tvSeriesId}/similar?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")

    fun getSimilarTvSeries(@Path("tvSeriesId") tvSeriesId: String): Call<SimilarTvSeriesModelClass>

}