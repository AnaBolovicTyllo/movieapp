package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.models.Movie
import kotlinx.coroutines.runBlocking

class MovieRepository (
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {
    private  val TAG = "GET POPULAR RESPONSE"


    // pukao network poziv, nisi dobila nikakav response, i dobila sam praznu listu
    // ako bi imali infoview dajemo mu  ova objasnjavanja
    // List<Movie> ---> List<Movie>?
    suspend fun getPopular(page: Int = 1): List<Movie> {
        return localDataSource.getPopularByPage(page) ?: run {
            val response = remoteDataSource.getsPopular()
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    localDataSource.insertPopularByPage(page, responseBody.results)
                    responseBody.results
                } else {
                    Log.d(TAG, "Response body is null")
                    emptyList()
                }
            } else {
                Log.d(TAG, response.message())
                emptyList()
            }
        }
    }

}