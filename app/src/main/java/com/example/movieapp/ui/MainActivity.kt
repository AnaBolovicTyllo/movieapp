package com.example.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.MovieLocalDataSource
import com.example.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter = MovieAdapter()

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.stateFlow.collect{ data ->
                adapter.setPopularMovies(data)
            }
        }
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.rvPopularMovies.adapter = adapter
        binding.rvPopularMovies.layoutManager =  GridLayoutManager(this, 3)

    }

    override fun onStart() {
        super.onStart()

    }
}

/**
 * TODO:
 *  1. Kreirati MainViewModel koji ce u init bloku pokupiti filmove iz repozitorijuma
 *  2. Iste te filmove ces unutar ViewModel-a mapirati koristeci MovieMapper, time dobijas List<MovieUi>
 *  3. Tu listu ces emitoviati kroz StateFlow
 *  4. Kada Activity pokupi te potake, prosledjuje ih adapteru
 */
