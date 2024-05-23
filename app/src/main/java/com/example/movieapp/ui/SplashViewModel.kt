package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    val movieRepository: MovieRepository
): ViewModel() {

    private val _goToMainScreen = MutableSharedFlow<Unit>()
    val goToMainScreen = _goToMainScreen.asSharedFlow()
    private val firstPage = 1

    init {
        loadData()
    }

     private fun loadData() {
        viewModelScope.launch {
            movieRepository.loadPopularMovies(firstPage)
            delay(1500L)
            _goToMainScreen.emit(Unit)
        }
    }

}