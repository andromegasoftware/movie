package com.andromega.moviepedia.movie_trailer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieYouTubeFragmentApi {

    @GET("movie/{movieId}/videos?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getFragments(@Path("movieId") movieId: String): Call<MovieFragmentModelClass>

}