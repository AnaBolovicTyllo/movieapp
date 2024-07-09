package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.models.ActorDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ViewState(
    val mediaItems: List<MediaItem> = emptyList(),
    val genres: List<String> = emptyList(),
    val cast: List<ActorDao> = emptyList(),
    val description: String? = null,
    val voteRate: String? = null,
    val voteCount: String? = null
)

class DetailsViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> get() = _viewState.asStateFlow()

    fun fetchImages(movieId: String) {
        viewModelScope.launch {
            val images = movieRepository.loadImages(movieId = movieId)
            _viewState.update { it.copy(mediaItems = it.mediaItems + images) }
        }
    }

    fun fetchVideos(movieId: String) {
        viewModelScope.launch {
            val videos = movieRepository.loadVideos(movieId = movieId)
            _viewState.update { it.copy(mediaItems = it.mediaItems + videos) }
        }
    }

    fun getGenres(movieId: String) {
        viewModelScope.launch {
            val genres = movieRepository.getById(movieId)?.genres?.map { it.name } ?: emptyList()
            _viewState.update { it.copy(genres = genres) }
        }
    }

    fun getDescription(movieId: String) {
        viewModelScope.launch {
            val movie = movieRepository.getById(movieId)
            val description = movie?.overview
            val voteRate = movie?.vote_average?.let { String.format("%.1f", it) } ?: ""
            val voteCount = movie?.vote_count.toString()
            val cast = movie?.credits?.cast ?: emptyList()
            _viewState.update {
                it.copy(
                    description = description,
                    voteRate = voteRate,
                    voteCount = voteCount,
                    cast = cast
                )
            }
        }
    }
}

