package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.repo.GithubUsersRepo
import com.szareckii.popularlibraries.mvp.view.MainView
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

class MainPresenter(val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.UsersScreen())
    }

    fun backClick() {
        router.exit()
    }

}