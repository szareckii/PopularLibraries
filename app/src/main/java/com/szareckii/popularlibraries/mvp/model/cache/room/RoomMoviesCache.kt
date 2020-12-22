package com.szareckii.popularlibraries.mvp.model.cache.room

import com.szareckii.popularlibraries.mvp.model.cache.MoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomMovie
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomMoviesCache(val db: Database): MoviesCache {

    override fun putMovies(movies: List<Movie>): Completable = Completable.fromAction {
        val roomMovies = movies.map { movie ->
            RoomMovie(
                    movie.id ?: "",
                    movie.title ?: "",
                    movie.image ?: "",
                    movie.year ?: "",
                    movie.imDbRating ?: 0.0f
            )
        }
        db.movieDao.insert(roomMovies)
    }.subscribeOn(Schedulers.io())

    override fun getMovies(): Single<List<Movie>> =
        Single.fromCallable {
            db.movieDao.getAll().map { roomMovie ->
                Movie(roomMovie.id, roomMovie.title, roomMovie.image, roomMovie.year, roomMovie.imDbRating)
            }
        }.subscribeOn(Schedulers.io())
}