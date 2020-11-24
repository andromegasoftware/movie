package com.andromega.moviepedia.cast_profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CastProfileApi {

    @GET("person/{castId}?api_key=dc92a7524c83f242a3237b2222941f00&language=en-US")

    fun getCastProfileData(@Path("castId") castId: String): Call<CastProfileModelClass>
}