package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.models.MovieMapper
import com.example.movieapp.models.MovieUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(emptyList<MovieUi>())
    val stateFlow: StateFlow<List<MovieUi>> get() = _stateFlow.asStateFlow()

    private val totalPageCount = 20
    private var currentPage = 1
    val isLastPage = currentPage == totalPageCount
    var isLoading = false

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true

            val list = movieRepository.loadPopularMovies(currentPage).map { movieDto ->
                MovieMapper.map(movieDto)
            }
            _stateFlow.update {
                it+list
            }

            isLoading = false
        }
    }

    fun loadNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            currentPage++
            val movieDtos = movieRepository.loadPopularMovies(currentPage)
            val listOfPopular = movieDtos.map { movieDto -> MovieMapper.map(movieDto) }
            withContext(Dispatchers.Main) {
                _stateFlow.update {
                    it+listOfPopular
                }
            }
        }
    }
}