package com.andromega.moviepedia.tv_series_reviews

import java.io.Serializable

class TvSeriesResultReviewModelClass(
    val author: String,
    val content: String,
    val id: String,
    val url: String
): Serializable {
}