package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class ImageResponse (
    @SerializedName("backdrops")
    val backdrops: List<ImageDetails>,
)