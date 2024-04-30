package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdropPath")
    val backdropPath: String? = null,
    @SerializedName("genreIds")
    val genreIds: List<Int> = listOf(),
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("originalLanguage")
    val originalLanguage: String? = null,
    @SerializedName("originalTitle")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("posterPath")
    val posterPath: String? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("voteAverage")
    val voteAverage: Double? = null,
    @SerializedName("voteCount")
    val voteCount: Int? = null
)
