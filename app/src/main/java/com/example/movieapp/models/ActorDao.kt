package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

class ActorDao (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profile_path: String,
    @SerializedName("character")
    val character: String,
    @SerializedName("known_for_department")
    val known_for_department: String
)

class CreditsDao(
    @SerializedName("cast")
    val cast: List<ActorDao>
)