package com.example.movieapp

import com.example.movieapp.data.MovieLocalDataSource
import com.example.movieapp.data.MovieRemoteDataSource
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.ui.MainViewModel
import com.example.movieapp.ui.SplashViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        MovieLocalDataSource()
    }
    viewModel {
        SplashViewModel(get())
    }
    viewModel {
        MainViewModel(get())
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Authorization", "Bearer "+ Constants.API_KEY)
                    builder.header("Content-Type", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }
    }
    single {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get<OkHttpClient.Builder>().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieRemoteDataSource::class.java)
    }
    single {
        MovieRepository(get(), get())
    }
    single {
        MovieLocalDataSource()
    }
}