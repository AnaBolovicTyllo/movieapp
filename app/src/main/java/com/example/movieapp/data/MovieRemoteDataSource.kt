package com.example.movieapp.data

import com.example.movieapp.models.GenreDto
import com.example.movieapp.models.ImageDetails
import com.example.movieapp.models.ImageResponse
import com.example.movieapp.models.MovieDetailsDto
import com.example.movieapp.models.MovieDto
import com.example.movieapp.models.MovieResponse
import com.example.movieapp.models.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("3/movie/{movie_id}")
    suspend fun getsPopularById(
        @Path("movie_id")
        movieId: String,
        @Query("include_adult")
        includeAdult: Boolean = false,
        @Query("language")
        language: String = "en-US",
        @Query("append_to_response")
        appendToResponse: String = "credits",
        @Query("api_key")
        apiKey: String
    ): Response<MovieDetailsDto>

    @GET("3/genre/movie/list")
    suspend fun getGenres(
        @Query("language")
        language: String = "en-US",
        @Query("api_key")
        apiKey: String
    ): Response<GenreDto>

    @GET("3/movie/{movie_id}/videos")
    suspend fun getsVideos(
        @Path("movie_id")
        movieId: String,
        @Query("api_key")
        apiKey: String
    ): Response<VideoResponse>

    @GET("3/movie/{movie_id}/images")
    suspend fun getsImages(
        @Path("movie_id")
        movieId: String,
        @Query("api_key")
        apiKey: String

    ): Response<ImageResponse>

}


