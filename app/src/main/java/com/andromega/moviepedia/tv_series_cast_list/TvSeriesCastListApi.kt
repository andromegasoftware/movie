package com.andromega.moviepedia.tv_series_cast_list

import com.andromega.moviepedia.movie_cast.MovieCastListModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvSeriesCastListApi {

    @GET("tv/{tvSeriesId}/credits?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getTvSeriesCasts(@Path("tvSeriesId") tvSeriesId: String): Call<TvSeriesCastListModelClass>
}