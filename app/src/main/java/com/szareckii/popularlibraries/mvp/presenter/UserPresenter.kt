package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.view.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(private val router: Router, private val user: GithubUser): MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserLogin(user.login)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}