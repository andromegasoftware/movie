package com.andromega.moviepedia.movies

import com.google.gson.annotations.SerializedName

class CircularMovieModelClass(@SerializedName("title")var MovieName:String,
                              @SerializedName("poster_path")var picture:String,
                              var id: Int,
                              @SerializedName("backdrop_path")var picturePoster:String) {
}