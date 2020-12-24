package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.repo.IMoviesRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IUserListPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.mvp.view.listUsers.UserItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter(): MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IMoviesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler

    class UserListPresenter: IUserListPresenter{
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val movies = mutableListOf<Movie>()

        override fun bindView(view: UserItemView) {
            val movie = movies[view.pos]
            movie.title?.let { view.setLogin(it) }
            movie.image?.let { view.loadImage(it) }
        }

        override fun getCount() = movies.size
    }

    val userListPresenter = UserListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.UserScreen(userListPresenter.movies[itemView.pos]))
        }
    }

    private fun loadData() {
         usersRepo.getMoviesList()
            .observeOn(uiScheduler)
            .subscribe({ movies ->
                userListPresenter.movies.clear()
                userListPresenter.movies.addAll(movies)
                viewState.updateUsersList()
            }, {
                println("Error: ${it.message}")

            }).addTo(compositeDisposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}