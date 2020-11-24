package com.andromega.moviepedia.tv_series_cast_list


import com.google.gson.annotations.SerializedName

data class TvCastModelClass(
    val adult: Boolean,
    @SerializedName("character")
    val castName: String,
    @SerializedName("credit_id")
    val creditId: String,
    val gender: Int,
    @SerializedName("id")
    val castId: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val pictureLink: String
)