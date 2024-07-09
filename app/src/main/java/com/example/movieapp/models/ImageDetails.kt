package com.example.movieapp.models

import com.example.movieapp.Constants
import com.example.movieapp.ui.MediaItem
import com.google.gson.annotations.SerializedName

data class ImageDetails(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    val height: Int,
    val iso6391: String?,
    @SerializedName("file_path")
    val filePath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val width: Int
)

object ImageMapper {
    fun map(dto: ImageDetails): MediaItem{
        return MediaItem(
            type = MediaItem.TYPE_IMAGE,
            url = Constants.DETAILS_BASE_URL + dto.filePath
        )
    }
}