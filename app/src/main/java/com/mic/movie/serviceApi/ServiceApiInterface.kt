package com.mic.movie.serviceApi

import com.mic.movie.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApiInterface {
    @GET("top_rated")
    fun getTopRatedMovies(
        @Query("api_key")apiKey:String
    ): Call<Movies>
}