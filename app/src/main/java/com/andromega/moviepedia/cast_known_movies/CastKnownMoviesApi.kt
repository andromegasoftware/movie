package com.andromega.moviepedia.cast_known_movies

import com.andromega.moviepedia.data.vo.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CastKnownMoviesApi {

    @GET("person/{castId}/combined_credits?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getCastKnownMovies(@Path("castId") castId: String): Call<CastKnownModelClass>
}