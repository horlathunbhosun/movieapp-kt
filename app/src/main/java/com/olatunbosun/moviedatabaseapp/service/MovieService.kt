package com.olatunbosun.moviedatabaseapp.service
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.olatunbosun.moviedatabaseapp.movieinterface.MovieService

fun createMovieService(token: String): MovieService {
    // Create an Interceptor to add the Bearer token
    val authInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val requestWithAuth: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        chain.proceed(requestWithAuth)
    }

    // Build OkHttpClient with the Interceptor
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    // Build Retrofit with OkHttpClient
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MovieService::class.java)
}