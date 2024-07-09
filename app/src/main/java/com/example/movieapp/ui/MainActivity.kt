package com.example.movieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.MovieUi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),IMovieOnClickListener {

    private val adapter = MovieAdapter()

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private var layoutManager = GridLayoutManager(this, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        adapter.setOnClickListener(this)

        setContentView(binding.root)
        observeData()
        setupAdapter()
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect { data ->
                adapter.add(data)
            }
        }
    }

    private fun setupAdapter() {
        with(binding){
            rvPopularMovies.adapter = adapter
            rvPopularMovies.layoutManager = layoutManager
            rvPopularMovies.addOnScrollListener(object :
                EndlessRecyclerViewScrollListener(layoutManager) {
                override fun loadMoreItems() {
                    viewModel.loadNextPage()
                }

                override fun isLastPage(): Boolean {
                    return viewModel.isLastPage
                }

                override fun isLoading(): Boolean {
                    return viewModel.isLoading
                }
            })
        }
    }

    override fun onClick(movie: MovieUi) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("id", movie.id)
        intent.putExtra("release_date", movie.releaseDate)
        intent.putExtra("overview", movie.overview)
        startActivity(intent)
    }
}
