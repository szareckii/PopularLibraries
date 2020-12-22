package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.repo.IGithubRepositoriesRepo
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

class UserPresenter(val user: Movie): MvpPresenter<UserView>() {

    @Inject lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var uiScheduler: Scheduler


    class RepositoryListPresenter: IRepositoryListPresenter {
        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

        val repositories = mutableListOf<GithubRepository>()

        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }

        override fun getCount() = repositories.size
    }

    val repositoryListPresenter = RepositoryListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        user.title?.let { viewState.setUserLogin(it) }

        repositoryListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.RepositoryScreen(user, repositoryListPresenter.repositories[itemView.pos]))
        }
    }

    private fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                repositoryListPresenter.repositories.clear()
                repositoryListPresenter.repositories.addAll(repositories)
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