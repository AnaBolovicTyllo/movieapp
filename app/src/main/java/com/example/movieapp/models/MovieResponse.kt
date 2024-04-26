package com.example.movieapp.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")           var page         : Int?               = null,
    @SerializedName("results")        var results      : List<Movie>        = listOf(),
    @SerializedName("total_pages")    var totalPages   : Int?               = null,
    @SerializedName("total_results")  var totalResults : Int?               = null
)