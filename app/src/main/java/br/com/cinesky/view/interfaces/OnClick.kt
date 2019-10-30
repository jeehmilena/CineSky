package br.com.cinesky.view.interfaces

import br.com.cinesky.model.MovieResult

interface OnClick {

    abstract fun clickMovie(movieResult: MovieResult)
}