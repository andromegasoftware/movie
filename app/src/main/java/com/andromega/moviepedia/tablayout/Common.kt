package com.andromega.moviepedia.tablayout

object Common {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    val newComingMoviesApi: NewComingMoviesApi
    get() = RetrofitClient.getClient(BASE_URL).create(NewComingMoviesApi::class.java)
}