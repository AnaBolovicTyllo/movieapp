package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.models.MovieDto

class MovieRepository (
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {

    // pukao network poziv, nisi dobila nikakav response, i dobila sam praznu listu
    // ako bi imali infoview dajemo mu  ova objasnjavanja
    // List<Movie> ---> List<Movie>?
    suspend fun getPopular(page: Int = 1): List<MovieDto> {
        return localDataSource.getPopular(page) ?: run {
            val response = remoteDataSource.getsPopular()
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