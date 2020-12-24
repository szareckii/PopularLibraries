package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepositoryPresenter(val movie: Movie, private val actor: Actor): MvpPresenter<RepositoryView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(movie.title ?: "")
        viewState.setTitle(actor.name ?: "")
        viewState.setForksCount(actor.asCharacter ?: "")
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}