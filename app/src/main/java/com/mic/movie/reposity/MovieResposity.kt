package com.mic.movie.reposity


import com.mic.movie.model.Movies
import com.mic.movie.serviceApi.ServiceApiInterface
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieResposity {
    private val serviceApiInterface: ServiceApiInterface

    companion object{
        const val BASE_URL="https://api.themoviedb.org/3/movie/"
        const val apiKey="492d89b92b96921e521f24bfe0a61d86"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serviceApiInterface = retrofit.create(ServiceApiInterface::class.java)
    }
    fun getTopRatedMovies(): Call<Movies> {
        return serviceApiInterface.getTopRatedMovies(apiKey)
    }
}