package com.example.movieapp.data

import com.example.movieapp.Constants
import com.example.movieapp.models.MovieDto

class MovieRepository (
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {

    suspend fun loadPopularMovies(page: Int): List<MovieDto> {
        return localDataSource.getPopular(page) ?: run {
            val response = remoteDataSource.getsPopular(page = page, apiKey = Constants.API_KEY)
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