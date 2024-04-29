package com.example.movieapp

import com.example.movieapp.data.MovieLocalDataSource
import com.example.movieapp.data.MovieRemoteDataSource
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.ui.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        MovieLocalDataSource()
    }
    viewModel {
        MainViewModel(get())
    }
    single<String> (named("BASE_URL")) {
        "https://api.themoviedb.org/"
    }
    single {
        Retrofit.Builder()
            .baseUrl(get(qualifier = named("BASE_URL")) as String)
            .client(get<OkHttpClient.Builder>().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieRemoteDataSource::class.java)
    }
    single {
        OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("accept", "application/json")
                    builder.header("Authorization", "Bearer 76422ff67ea5c659115400f1f574c025")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }
    }
    single {
        MovieRepository(get(), get())
    }
}