package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.repo.IGithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IUserListPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.mvp.view.listUsers.UserItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(val router: Router, val usersRepo: IGithubUsersRepo, val mainScheduler: Scheduler): MvpPresenter<UsersView>() {

    class UserListPresenter: IUserListPresenter{
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let { view.loadImage(it) }
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UserListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {itemView ->
            router.navigateTo(Screens.UserScreen(userListPresenter.users[itemView.pos]))
        }
    }

    private fun loadData() {
         usersRepo.getUsers()
            .observeOn(mainScheduler)
            .subscribe({ repos ->
                userListPresenter.users.clear()
                userListPresenter.users.addAll(repos)
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