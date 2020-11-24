package com.andromega.moviepedia.tablayout

import com.andromega.moviepedia.movies.NewComingMoviesModelClassNew
import com.andromega.moviepedia.movies.NewComingMoviesMovieList
import retrofit2.Call
import retrofit2.http.GET


interface NewComingMoviesApi {

    @GET("movie/popular?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&page=1")

    fun fecthNewComingMovies(): Call<NewComingMoviesMovieList>
}