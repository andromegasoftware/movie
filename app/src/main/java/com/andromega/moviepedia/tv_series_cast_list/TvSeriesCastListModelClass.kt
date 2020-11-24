package com.andromega.moviepedia.tv_series_cast_list


data class TvSeriesCastListModelClass(
    val cast: List<TvCastModelClass>,
    val crew: List<Crew>,
    val id: Int
)