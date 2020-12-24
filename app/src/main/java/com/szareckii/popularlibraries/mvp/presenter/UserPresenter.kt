package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.entity.MovieActorsList
import com.szareckii.popularlibraries.mvp.model.repo.IActorsRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IRepositoryListPresenter
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.mvp.view.listUsers.RepositoryItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter(val movie: Movie): MvpPresenter<UserView>() {

    @Inject lateinit var actorsRepo: IActorsRepo
    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler


    class RepositoryListPresenter: IRepositoryListPresenter {
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        val actors = mutableListOf<Actor>()

        override fun bindView(view: RepositoryItemView) {
            val actor = actors[view.pos]
            actor.name?.let { view.setName(it) }
        }

        override fun getCount() = actors.size
    }

    val repositoryListPresenter = RepositoryListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        movie.title?.let { viewState.setUserLogin(it) }

        repositoryListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.RepositoryScreen(movie, repositoryListPresenter.actors[itemView.pos]))
        }
    }

    private fun loadData() {
        actorsRepo.getActorsList(movie)
            .observeOn(uiScheduler)
            .subscribe({ actors ->
                repositoryListPresenter.actors.clear()
                repositoryListPresenter.actors.addAll(actors)
                viewState.updateUserReposList()
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