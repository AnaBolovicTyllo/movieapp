package com.example.movieapp.models

import com.example.movieapp.Constants
import com.example.movieapp.ui.MediaItem
import com.google.gson.annotations.SerializedName

data class VideoDetails(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("official")
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("id")
    val id: String
)

object VideoMapper {
    fun map(dto: VideoDetails): MediaItem{
        return MediaItem(
            url = Constants.POSTER_BASE_URL + dto.key,
            type = MediaItem.TYPE_VIDEO
        )
    }
}
