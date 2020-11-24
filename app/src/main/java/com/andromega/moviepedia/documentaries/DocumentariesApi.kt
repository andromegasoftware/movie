package com.andromega.moviepedia.documentaries

import com.andromega.moviepedia.comedy.ComedyMoviesMovieListModelClass
import retrofit2.Call
import retrofit2.http.GET

interface DocumentariesApi {
    @GET("https://api.themoviedb.org/3/discover/movie?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_year=2020&with_genres=99&without_keywords=158436%7C445%7C11530%7C7344%7C199723%7C206574%7C109520")

    fun getDocumentaries() : Call<DocumentariesMovieListModelClass>
}