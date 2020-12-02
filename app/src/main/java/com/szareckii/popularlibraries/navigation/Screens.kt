package com.szareckii.popularlibraries.navigation

import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.ui.fragment.UserFragment
import com.szareckii.popularlibraries.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen(): SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: GithubUser): SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}