package com.olatunbosun.moviedatabaseapp.model;

import com.google.gson.annotations.SerializedName

data class MovieResponse(
        val results: List<Movie>
)

data class Movie(
        val id: Int,
        val title: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("backdrop_path") val backdropPath: String,
        val overview: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int
)

