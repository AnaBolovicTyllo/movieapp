package com.example.movieapp.data

import com.example.movieapp.models.MovieDto

class MovieLocalDataSource {
    private val movieCacheByPage = mutableMapOf<Int, List<MovieDto>>()

    fun getPopular(pageNumber: Int = 1): List<MovieDto>? {
        return movieCacheByPage[pageNumber]?.toList()
    }

    fun insertPopular(pageNumber: Int = 1, movies: List<MovieDto>) {
        movieCacheByPage[pageNumber] = movies.toList()
    }
}