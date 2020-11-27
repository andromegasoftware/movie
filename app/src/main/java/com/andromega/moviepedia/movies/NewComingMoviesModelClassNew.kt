package com.andromega.moviepedia.movies


import com.google.gson.annotations.SerializedName

data class NewComingMoviesModelClassNew(

    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("backdrop_path")
    val backdropPath: String


    /*val adult: Boolean,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val popularity: Double,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,
    val revenue: Int,
    val runtime: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val video: Boolean,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("imdb_id")
    val imdbİd: String,
    val overview: String,
*/
)