package com.example.movieapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModel()
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

       enableEdgeToEdge()

       installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.getFamousMovies()
                viewModel.setViewModelReady()
                !viewModel.isReady.value
            }
        }

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}