package com.example.movieapp.data

import com.example.movieapp.models.GenreDto
import com.example.movieapp.models.MovieDto

class MovieLocalDataSource {
    private val movieCacheByPage = mutableMapOf<Int, List<MovieDto>>()
    private val genresCache = mutableMapOf<String, String>()

    fun getPopular(pageNumber: Int = 1): List<MovieDto>? {
        return movieCacheByPage[pageNumber]?.toList()
    }

    fun getGenres(id: String): String? {
        return genresCache[id]
    }

    fun insertPopular(pageNumber: Int = 1, movies: List<MovieDto>) {
        movieCacheByPage[pageNumber] = movies.toList()
    }

    fun insertGenre(id: String, title: String) {
        genresCache[id] = title
    }
}