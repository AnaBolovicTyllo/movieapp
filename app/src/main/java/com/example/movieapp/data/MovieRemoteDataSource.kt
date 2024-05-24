package com.example.movieapp.data

import com.example.movieapp.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRemoteDataSource {
    @GET("3/discover/movie")
    suspend fun getsPopular(
        @Query("include_adult")
        includeAdult: Boolean = false,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int,
        @Query("api_key")
        apiKey: String
    ): Response<MovieResponse>
}


