package com.andromega.moviepedia.similar_movies

import com.andromega.moviepedia.cast_known_movies.CastKnownModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SimilarMoviesApi {
    @GET("movie/{movieId}/similar?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")
    fun getSimilarMovies(@Path("movieId") movieId: String): Call<SimilarMoviesModelClass>
}