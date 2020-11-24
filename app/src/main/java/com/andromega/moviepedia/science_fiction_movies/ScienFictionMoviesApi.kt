package com.andromega.moviepedia.science_fiction_movies

import com.andromega.moviepedia.action_movies.ActionMoviesMovieListModelClass
import retrofit2.Call
import retrofit2.http.GET

interface ScienFictionMoviesApi {
    @GET("https://api.themoviedb.org/3/discover/movie?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=878")

    fun getActionMovies() : Call<ScienceFictionMoviesMovieListModelClass>
}