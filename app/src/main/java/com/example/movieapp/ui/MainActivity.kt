package com.example.movieapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.MovieLocalDataSource
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.MovieAdapter
import com.example.movieapp.ui.SplashViewModel
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter()

    private lateinit var binding: ActivityMainBinding
    private val localDataSource: MovieLocalDataSource by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvPopularMovies.adapter = adapter
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        runBlocking {
            localDataSource.getPopular()?.let { adapter.setPopularMovies(it) }
        }

    }
}

/**
 * TODO:
 *  1. Kreirati MainViewModel koji ce u init bloku pokupiti filmove iz repozitorijuma
 *  2. Iste te filmove ces unutar ViewModel-a mapirati koristeci MovieMapper, time dobijas List<MovieUi>
 *  3. Tu listu ces emitoviati kroz StateFlow
 *  4. Kada Activity pokupi te potake, prosledjuje ih adapteru
 */
