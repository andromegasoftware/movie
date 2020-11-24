package com.andromega.moviepedia.data.vo


import com.google.gson.annotations.SerializedName

data class MovieDetails(

    val title: String,

    val overview: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("backdrop_path")
    val posterPath: String,

    val genres: List<Genre>,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("id")
    val movieId: Int,

   /* val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val budget: Int,
    val homepage: String,
    @SerializedName("imdb_id")
    val imdbİd: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val popularity: Double,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    val revenue: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val video: Boolean,
    @SerializedName("vote_count")
    val voteCount: Int*/
)