package com.example.movieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModel()
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       enableEdgeToEdge()
       setContentView(R.layout.activity_main)

//       lifecycleScope.launch {
//           repeatOnLifecycle(Lifecycle.State.STARTED){
//               viewModel.initSplashScreen.collect {
//                    installSplashScreen()
//               }
//           }
//       }
       observeFlowSafely(viewModel.initSplashScreen){
           // Radimo animaciju
       }

//       lifecycleScope.launch {
//           repeatOnLifecycle(Lifecycle.State.STARTED){
//               viewModel.goToMainScreen.collect {
//                   // Idemo na sledeci ekran
//               }
//           }
//       }
       observeFlowSafely(viewModel.goToMainScreen){
           // Idemo na drugi ekran
       }
    }
}

fun <T> AppCompatActivity.observeFlowSafely(flow: Flow<T>, callback: (T) -> Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                callback(it)
            }
        }
    }
}