package com.example.movieapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.models.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
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