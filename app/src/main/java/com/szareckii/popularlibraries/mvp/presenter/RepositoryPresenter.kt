package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val router: Router, private val user: GithubUser,
                          private val githubRepository: GithubRepository): MvpPresenter<RepositoryView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount(githubRepository.forksCount ?: "")
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}