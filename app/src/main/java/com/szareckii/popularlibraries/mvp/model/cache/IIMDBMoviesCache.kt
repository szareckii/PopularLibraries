package com.szareckii.popularlibraries.mvp.model.cache

import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IIMDBMoviesCache {
    fun putMovies(movies: List<IMDBMovie>): Completable
    fun getMovies(): Single<List<IMDBMovie>>
}