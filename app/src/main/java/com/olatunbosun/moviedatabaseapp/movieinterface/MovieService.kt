package com.olatunbosun.moviedatabaseapp.movieinterface

import com.olatunbosun.moviedatabaseapp.model.Movie
import com.olatunbosun.moviedatabaseapp.model.MovieResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/popular?api_key=YOUR_API_KEY")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/{movie_id}?api_key=YOUR_API_KEY")
    suspend fun getMovieDetails(@retrofit2.http.Path("movie_id") movieId: String): Movie
}