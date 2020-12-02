package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubUserRepository
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IRepositoryListPresenter
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import com.szareckii.popularlibraries.mvp.view.listUsers.RepositoryItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepositoryPresenter(private val router: Router, private val user: GithubUser, val usersRepo: RetrofitGithubUsersRepo, private val mainScheduler: Scheduler): MvpPresenter<RepositoryView>() {

    class RepositoryListPresenter: IRepositoryListPresenter {
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        val repository = mutableListOf<GithubUserRepository>()

        override fun bindView(view: RepositoryItemView) {
            val repository = repository[view.pos]
            view.setNameRepos(repository.name)
        }

        override fun getCount() = repository.size
    }

    val repositoryListPresenter = RepositoryListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setUserLogin(user.login)
        loadData(user.reposUrl)
//
//        userListPresenter.itemClickListener = {view ->
//            val reposUrl = userListPresenter.users[view.pos].reposUrl
//            router.navigateTo(Screens.UserScreen(userListPresenter.users[view.pos]))
//        }
    }

    private fun loadData(url: String) {
        usersRepo.getUserRepos(url)
            .observeOn(mainScheduler)
            .subscribe({ repos ->
                repositoryListPresenter.repository.clear()
                repositoryListPresenter.repository.addAll(repos)
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