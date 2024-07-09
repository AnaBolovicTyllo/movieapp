package com.example.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.ActivityDetailsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val adapter = MediaAdapter()
    private val genreAdapter = GenreAdapter()
    private val castAdapter = CastAdapter()
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id") ?: return
        viewModel.fetchVideos(id)
        viewModel.fetchImages(id)
        viewModel.getGenres(id)
        viewModel.getDescription(id)

        setupBanner()
        setupDetailsAdapter()
        setupGenreAdapter()
        setupCastAdapter()
        observeData()
        observeGenres()
        observeDescription()
        observeVotes()
        observeCastData()
    }

    private fun setupGenreAdapter() {
        binding.rvGenres.adapter = genreAdapter
        binding.rvGenres.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupDetailsAdapter() {
        binding.rvMedia.adapter = adapter
        binding.rvMedia.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupCastAdapter() {
        binding.rvCast.adapter = castAdapter
        binding.rvCast.layoutManager = LinearLayoutManager(this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setupBanner() {
        with(binding) {
            tvBannerHeding.text = intent.getStringExtra("title")
            tvYear.text = intent.getStringExtra("release_date")?.substring(0, 4)
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.viewState.collect { data ->
                adapter.add(data.mediaItems)
            }
        }
    }

    private fun observeGenres() {
        lifecycleScope.launch {
            viewModel.viewState.collect { data ->
                genreAdapter.add(data.genres)
            }
        }
    }

    private fun observeCastData() {
        lifecycleScope.launch {
            viewModel.viewState.collect {data ->
                castAdapter.add(data.cast)
            }
        }
    }

    private fun observeDescription() {
        lifecycleScope.launch {
            viewModel.viewState.collect { data ->
                binding.tvDescription.text = data.description
                binding.tvDescription.setOnClickListener {

                    val currentLines = binding.tvDescription.layout.getLineCount()
                    val maxLines = binding.tvDescription.maxLines

                    if (currentLines == maxLines) {
                        binding.tvDescription.maxLines = Int.MAX_VALUE
                        binding.tvDescription.text = data.description
                    } else {
                        binding.tvDescription.maxLines = 3
                        binding.tvDescription.text = data.description
                    }
                }
            }
        }
    }

    private fun observeVotes() {
        lifecycleScope.launch {
            viewModel.viewState.collect { data ->
                binding.tvVote.text = data.voteRate
            }
        }

        lifecycleScope.launch {
            viewModel.viewState.collect { data ->
                binding.tvVoteCount.text = data.voteCount
            }
        }
    }
}
