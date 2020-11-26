package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.repo.GithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IUserListPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.mvp.view.list.UserItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(val router: Router, val usersRepo: GithubUsersRepo, val mainScheduler: Scheduler): MvpPresenter<UsersView>() {

    class UserListPresenter: IUserListPresenter{
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        val users = mutableListOf<GithubUser>()

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val userListPresenter = UserListPresenter()
    lateinit var disposable: Disposable

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        userListPresenter.itemClickListener = {view ->
            router.navigateTo(Screens.UserScreen(userListPresenter.users[view.pos]))
        }
    }

    private fun loadData() {
        userListPresenter.users.clear()

        disposable = usersRepo.getUsersNotNull()
            .subscribeOn(Schedulers.io())
            .observeOn(mainScheduler)
            .subscribe(){
            userListPresenter.users.add(it)
            viewState.updateUsersList()
            }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    fun fragmentDestroy() = disposable.dispose()
}