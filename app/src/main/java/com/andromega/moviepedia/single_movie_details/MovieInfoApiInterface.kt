package com.andromega.moviepedia.single_movie_details

import android.graphics.Movie
import com.andromega.moviepedia.data.vo.MovieDetails
import com.andromega.moviepedia.tablayout.MoviesFragment
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInfoApiInterface {


    @GET("movie/{movieId}?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun fetchAllData(@Path("movieId") movieId: String): Call<MovieDetails>

}