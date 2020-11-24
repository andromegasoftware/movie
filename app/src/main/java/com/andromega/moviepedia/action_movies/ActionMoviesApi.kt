package com.andromega.moviepedia.action_movies

import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesMovieList
import retrofit2.Call
import retrofit2.http.GET

interface ActionMoviesApi {
    @GET("discover/movie?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28")

    fun getActionMovies() : Call<ActionMoviesMovieListModelClass>
}