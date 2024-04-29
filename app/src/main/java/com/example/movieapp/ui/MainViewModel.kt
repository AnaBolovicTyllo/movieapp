package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

     fun getFamousMovies() {
        viewModelScope.launch {
            movieRepository.getPopular()
        }
    }

    fun setViewModelReady() {
        viewModelScope.launch{
            delay(3000)
            _isReady.value = true
        }
    }
}