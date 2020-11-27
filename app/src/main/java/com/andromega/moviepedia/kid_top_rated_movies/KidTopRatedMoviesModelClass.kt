package com.andromega.moviepedia.kid_top_rated_movies

import com.google.gson.annotations.SerializedName

class KidTopRatedMoviesModelClass
    (@SerializedName("title")var MovieName:String,
     @SerializedName("poster_path")var picture:String,
     var id: Int,
     @SerializedName("backdrop_path")var picturePoster:String)
{
}