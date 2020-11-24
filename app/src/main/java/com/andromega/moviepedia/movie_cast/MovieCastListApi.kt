package com.andromega.moviepedia.movie_cast

import com.andromega.moviepedia.data.vo.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieCastListApi {

    @GET("movie/{movieId}/credits?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getMovieCasts(@Path("movieId") movieId: String): Call<MovieCastListModelClass>
}