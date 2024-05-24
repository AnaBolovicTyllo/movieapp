package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.movieapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        startAnimation()

        observeFlowSafely(viewModel.goToMainScreen) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun startAnimation() {
        val imageView = findViewById<ImageView>(R.id.splashPopCorn)

        val rotateAnimation = RotateAnimation(0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000

        val scaleAnimation = ScaleAnimation(1f, 1.2f, 1f, 1.2f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1000

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(rotateAnimation)
        animationSet.addAnimation(scaleAnimation)

        imageView.startAnimation(animationSet)
    }
}

fun <T> AppCompatActivity.observeFlowSafely(flow: Flow<T>, callback: (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                callback(it)
            }
        }
    }
}
