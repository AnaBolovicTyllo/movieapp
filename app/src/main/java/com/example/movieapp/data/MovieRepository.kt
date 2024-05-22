package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.models.MovieDto

class MovieRepository (
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {

    suspend fun loadPopularMovies(page: Int = 0): List<MovieDto> {
        return localDataSource.getPopular(page) ?: run {
            val response = remoteDataSource.getsPopular(page = page, apiKey = "76422ff67ea5c659115400f1f574c025")
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    localDataSource.insertPopular(page, responseBody.results)
                    responseBody.results
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }
        }
    }

}