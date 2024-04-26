package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("adult")            var adult: Boolean?           = null,
    @SerializedName("backdropPath")     var backdropPath: String?     = null,
    @SerializedName("genreIds")         var genreIds: List<Int>       = listOf(),
    @SerializedName("id")               var id: Int?                  = null,
    @SerializedName("originalLanguage") var originalLanguage: String? = null,
    @SerializedName("originalTitle")    var originalTitle: String?    = null,
    @SerializedName("overview")         var overview: String?         = null,
    @SerializedName("popularity")       var popularity: Double?       = null,
    @SerializedName("posterPath")       var posterPath: String?       = null,
    @SerializedName("releaseDate")      var releaseDate: String?      = null,
    @SerializedName("title")            var title: String?            = null,
    @SerializedName("video")            var video: Boolean?           = null,
    @SerializedName("voteAverage")      var voteAverage: Double?      = null,
    @SerializedName("voteCount")        var voteCount: Int?           = null
)
