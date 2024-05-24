package com.example.movieapp.models

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.roundToInt

data class MovieDto(
    @SerializedName("adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)

object MovieMapper {
    fun map(dto: MovieDto): MovieUi{
        return MovieUi(
            title = dto.title ?: "Unknown",
            posterPath = dto.posterPath,
            voteAverage = dto.voteAverage?.roundDecimal()
        )
    }
}

data class MovieUi(
    val title: String,
    val posterPath: String?,
    val voteAverage: Double?
)

private fun Double.roundDecimal(rounding: Int = 1): Double {
    val rounding = 10f.pow(rounding)
    return (this * rounding).roundToInt() / rounding.toDouble()
}
