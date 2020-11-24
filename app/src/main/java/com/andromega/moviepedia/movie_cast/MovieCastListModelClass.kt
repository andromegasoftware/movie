package com.andromega.moviepedia.movie_cast

import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.google.gson.annotations.SerializedName

class MovieCastListModelClass(@SerializedName("cast")val results: List<MovieCastModelClass>) {
}