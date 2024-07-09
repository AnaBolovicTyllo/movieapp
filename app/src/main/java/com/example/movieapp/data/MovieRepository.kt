package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.Constants
import com.example.movieapp.models.GenreDto
import com.example.movieapp.models.ImageMapper
import com.example.movieapp.models.MovieDetailsDto
import com.example.movieapp.models.MovieDto
import com.example.movieapp.models.VideoMapper
import com.example.movieapp.ui.MediaItem

class MovieRepository(
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

    suspend fun loadImages(movieId: String): List<MediaItem> {
        val response = remoteDataSource.getsImages(movieId = movieId, apiKey = Constants.API_KEY)
        if (response.isSuccessful) {
            val responseBody = response.body()
            return if (responseBody != null) {
                if(responseBody.backdrops.size >= 7) {
                    responseBody.backdrops
                        .map { ImageMapper.map(it) }
                        .subList(0,7)
                }
                else
                    responseBody.backdrops
                        .map { ImageMapper.map(it) }
            } else {
                emptyList()
            }
        } else {
           return  emptyList()
        }
    }

    suspend fun loadVideos(movieId: String): List<MediaItem> {
        val response = remoteDataSource.getsVideos(movieId = movieId, apiKey = Constants.API_KEY)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            responseBody?.results?.map { VideoMapper.map(it) }?.subList(0,1) ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun loadGenres() {
        val response = remoteDataSource.getGenres(apiKey = Constants.API_KEY)
        if (response.isSuccessful) {
            val responseBody = response.body()
            localDataSource.insertGenre(responseBody!!.id, responseBody.name)
        }
    }

    suspend fun getById(id: String) : MovieDetailsDto? {
        val response = remoteDataSource.getsPopularById(movieId = id, apiKey = Constants.API_KEY)
        return if(response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}