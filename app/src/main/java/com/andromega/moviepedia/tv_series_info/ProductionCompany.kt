package com.andromega.moviepedia.tv_series_info


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: Any,
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)