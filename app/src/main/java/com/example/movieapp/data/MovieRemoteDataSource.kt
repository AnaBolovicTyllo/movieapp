package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MovieRemoteDataSource {
    @GET("3/discover/movie")
    suspend fun getsPopular(
        @Query("include_adult")
        includeAdult: Boolean = false,
        @Query("language")
        language: String = "en-US",
        @Query("page")
        page: Int = 1
    ): Response<MovieResponse>
}


