package com.szareckii.popularlibraries.mvp.model.cache.room

import com.szareckii.popularlibraries.mvp.model.cache.IIMDBMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomIMDBMovie
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomIMDBMoviesCache(val db: Database): IIMDBMoviesCache {

    override fun putMovies(movies: List<IMDBMovie>): Completable = Completable.fromAction {
        val roomMovies = movies.map { movie ->
            RoomIMDBMovie(
                    movie.id ?: "",
                    movie.title ?: "",
                    movie.image ?: "",
                    movie.year ?: "",
                    movie.imDbRating ?: ""
            )
        }
        db.movieDao.insert(roomMovies)
    }.subscribeOn(Schedulers.io())

    override fun getMovies(): Single<List<IMDBMovie>> =
        Single.fromCallable {
            db.movieDao.getAll().map { roomMovie ->
                IMDBMovie(roomMovie.id, roomMovie.title, roomMovie.image, roomMovie.year, roomMovie.imDbRating)
            }
        }.subscribeOn(Schedulers.io())
}