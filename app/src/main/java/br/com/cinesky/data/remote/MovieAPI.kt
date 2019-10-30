package br.com.cinesky.data.remote

import br.com.cinesky.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieAPI {

    @GET("Movies")
    fun getAllMovies() : Observable<List<MovieResult>>
}