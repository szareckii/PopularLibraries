package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import com.szareckii.popularlibraries.mvp.model.entity.MoviesList
import io.reactivex.rxjava3.core.Single

interface IIMDBMoviesRepo {

    fun getMoviesList(): Single<List<IMDBMovie>>
//    fun getMoviesList(): Single<MoviesList>
}