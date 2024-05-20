package com.example.movieapp.models

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.math.RoundingMode

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int> = listOf(),
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)

object MovieMapper{
    fun map(dto: MovieDto): MovieUi{
        return MovieUi(
            title = dto.title ?: "Unknown",
            posterPath = dto.posterPath,
            voteAverage = dto.voteAverage?.times(10)?.toInt()?.toDouble()?.div(10)?.toBigDecimal()?.setScale(1, RoundingMode.DOWN)?.toDouble()
        )
    }
}

data class MovieUi(
    val title: String,
    val posterPath: String?,
    val voteAverage: Double?
)
