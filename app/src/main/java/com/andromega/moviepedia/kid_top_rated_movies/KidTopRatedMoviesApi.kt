package com.andromega.moviepedia.kid_top_rated_movies

import com.andromega.moviepedia.action_movies.ActionMoviesMovieListModelClass
import retrofit2.Call
import retrofit2.http.GET

interface KidTopRatedMoviesApi {

    @GET("discover/movie?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=vote_average.desc&include_adult=false&include_video=false&page=1&with_genres=16%2C10751")

    fun getKidTopRatedMovies() : Call<KidTopRatedMoviesListModelClass>
}