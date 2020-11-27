package com.andromega.moviepedia.actor_search

import com.google.gson.annotations.SerializedName

class ActorSearchModelClass(

    @SerializedName("id")
    val movieId: Int,
    @SerializedName("profile_path")
    val posterPath: String,
    @SerializedName("name")
    val title: String,
    @SerializedName("known_for_department")
    val job: String,
    val popularity: Number,

) {
}