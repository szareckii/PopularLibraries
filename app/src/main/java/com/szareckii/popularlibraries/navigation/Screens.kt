package com.szareckii.popularlibraries.navigation

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.ui.fragment.RepositoryFragment
import com.szareckii.popularlibraries.ui.fragment.UserFragment
import com.szareckii.popularlibraries.ui.fragment.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen(): SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance()
    }

    class UserScreen(private val user: Movie): SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepositoryScreen(private val user: Movie, private val actor: Actor): SupportAppScreen() {
        override fun getFragment() = RepositoryFragment.newInstance(user, actor)
    }
}