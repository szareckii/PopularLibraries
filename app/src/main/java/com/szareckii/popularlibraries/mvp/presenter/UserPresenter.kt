package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.repo.GithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IUserListPresenter
import com.szareckii.popularlibraries.mvp.view.MainView
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.mvp.view.list.UserItemView
import kotlinx.android.synthetic.main.fragment_user.*
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(val router: Router, val user: GithubUser): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserLogin()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}