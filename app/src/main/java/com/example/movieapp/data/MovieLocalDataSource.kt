package com.example.movieapp.data

import com.example.movieapp.models.Movie

class MovieLocalDataSource {
    private val movieCacheByPage = mutableMapOf<Int, List<Movie>>()

    fun getPopularByPage(pageNumber: Int = 1): List<Movie>? {
        return movieCacheByPage[pageNumber]?.toList()
    }

    fun insertPopularByPage(pageNumber: Int = 1, movies: List<Movie>) {
        movieCacheByPage[pageNumber] = movies.toList()
    }
}