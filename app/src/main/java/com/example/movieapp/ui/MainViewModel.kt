package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.models.MovieMapper
import com.example.movieapp.models.MovieUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.runBlocking

class MainViewModel(
    val movieRepository: MovieRepository
): ViewModel() {

    private val _stateFlow = MutableStateFlow(emptyList<MovieUi>())
    val stateFlow: StateFlow<List<MovieUi>> get() = _stateFlow.asStateFlow()

    init {
        listMovies()
    }
    fun listMovies() {
        CoroutineScope(Dispatchers.Main).launch  {
            var listOfPopular = mutableListOf<MovieUi>()
            movieRepository.loadPopularMovies().forEach { movieDto ->
                listOfPopular.add(MovieMapper.map(movieDto))
            }
            _stateFlow.emit(listOfPopular)
        }
    }
}