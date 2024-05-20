package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _initSplashScreen = MutableSharedFlow<Unit>()
    val initSplashScreen = _initSplashScreen.asSharedFlow()

    private val _goToMainScreen = MutableSharedFlow<Unit>()
    val goToMainScreen = _goToMainScreen.asSharedFlow()

     fun loadData() {
        viewModelScope.launch {
            _initSplashScreen.emit(Unit)
            movieRepository.getPopular()
            delay(3000L)
            _goToMainScreen.emit(Unit)
        }
    }

}