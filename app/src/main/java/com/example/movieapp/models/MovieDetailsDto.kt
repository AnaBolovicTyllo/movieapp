package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsDto(
    @SerializedName("adult")
    val adult: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<GenreDto>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("credits")
    val credits: CreditsDao
)