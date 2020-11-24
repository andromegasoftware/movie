package com.andromega.moviepedia.tv_series_reviews

import com.andromega.moviepedia.movie_reviews.MovieReviewsModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvSeriesReviewsApi {

    @GET("tv/{tvSeriesId}/reviews?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")


    fun getTvSeriesRevies(@Path("tvSeriesId") tvSeriesId: String): Call<TvReviewsModelClass>
}