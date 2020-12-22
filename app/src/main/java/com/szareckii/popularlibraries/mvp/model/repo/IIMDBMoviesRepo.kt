package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Single

interface MoviesRepo {

    fun getMoviesList(): Single<List<Movie>>
//    fun getMoviesList(): Single<MoviesList>
}