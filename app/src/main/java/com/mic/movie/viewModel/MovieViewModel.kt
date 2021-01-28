package com.mic.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mic.movie.model.Movies
import com.mic.movie.reposity.MovieResposity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import  com.mic.movie.model.Result

class MoviesViewModel : ViewModel() {
    var topRatedMovies: MutableLiveData<List<Result>> = MutableLiveData()
    var LoadError: MutableLiveData<Boolean> = MutableLiveData()
    var Loading: MutableLiveData<Boolean> = MutableLiveData()

    //Getter
    fun getTop_RatedMovies(apiKey: String): LiveData<List<Result>> = topRatedMovies
    fun getError(): LiveData<Boolean> = LoadError
    fun getLoading(): LiveData<Boolean> = Loading

    private val moviesApi: MovieResposity = MovieResposity()

    //Setter
    fun loadMovies() {
        Loading.value = true
        val apiCall = moviesApi.getTopRatedMovies()
        apiCall.enqueue(object : Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                println("Error")
                LoadError.value = true
                Loading.value = false

            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                println("RESPONSE ${response.body().toString()}")
                response.isSuccessful.let {
                    Loading.value = false
                    val resultList: List<Result> = response.body()?.results ?: emptyList()
                    topRatedMovies.value = resultList
                }
            }

        })
    }
}