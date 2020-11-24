package com.andromega.moviepedia.tv_series_trailer

import com.andromega.moviepedia.movie_trailer.MovieFragmentModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvYouTubeTrailerApi {

    @GET("tv/{tvSeriesId}/videos?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getTvTrailer(@Path("tvSeriesId") tvSeriesId: String): Call<TvSeriesTrailerModelClass>
}