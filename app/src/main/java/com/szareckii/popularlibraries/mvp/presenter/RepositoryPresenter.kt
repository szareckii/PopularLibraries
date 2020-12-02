package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubReposFork
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubUserRepository
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val router: Router, private val user: GithubUser, private val repository: GithubUserRepository,
                          private val usersRepo: RetrofitGithubUsersRepo, private val mainScheduler: Scheduler): MvpPresenter<RepositoryView>() {

    private val listForks = mutableListOf<GithubReposFork>()

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        init()
        viewState.setLogin(user.login)
        viewState.setRepository(repository.name)
        loadData(repository.forksUrl)
    }

    private fun init() {
        viewState.setLogin("")
        viewState.setRepository("")
        viewState.setNumberOfForks(0)
    }

    private fun loadData(url: String) {
        usersRepo.getUserForks(url)
            .observeOn(mainScheduler)
            .subscribe({ fork ->
                listForks.clear()
                listForks.addAll(fork)
                viewState.setNumberOfForks(listForks.size)
            }, {
                println("Error: ${it.message}")

            }).addTo(compositeDisposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}