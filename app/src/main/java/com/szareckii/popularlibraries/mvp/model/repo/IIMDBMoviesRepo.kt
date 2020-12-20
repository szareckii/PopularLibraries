package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import io.reactivex.rxjava3.core.Single

interface IIMDBMoviesRepo {

    fun getMovies(): Single<List<IMDBMovie>>
}