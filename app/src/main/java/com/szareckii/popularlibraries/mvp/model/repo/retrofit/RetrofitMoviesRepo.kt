package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.MoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.MoviesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitMoviesRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheUsers: MoviesCache):
        MoviesRepo {

//    override fun getMoviesList(): Single<List<IMDBMovie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
    override fun getMoviesList(): Single<List<Movie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getMoviesList().flatMap { moviesList ->
                moviesList.items?.let { movies ->
                    cacheUsers.putMovies(movies).andThen(Single.just(movies))
                }
            }
        }
        else {
            api.getMoviesList().flatMap { moviesList ->
                moviesList.items?.let {
                    cacheUsers.getMovies()
                }
            }
        }
    }.subscribeOn(Schedulers.io())

//    override fun getMovies(): Single<List<IMDBMovie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
//        if (isOnline) {
//            api.getUsers().flatMap { users ->
//                cacheUsers.putMovies(users).andThen(Single.just(users))
//            }
//        } else {
//            cacheUsers.getMovies()
//        }
//    }.subscribeOn(Schedulers.io())
}


//RetrofitGithubUsersRepo
//override fun getMoviesList(): Single<MoviesList> = api.getMoviesList().subscribeOn(Schedulers.io())
