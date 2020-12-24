package com.szareckii.popularlibraries.di.movie

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.di.repository.ActorSubcomponent
import com.szareckii.popularlibraries.di.movie.module.MovieModule
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
        modules = [
            MovieModule::class
        ]
)
interface MovieSubcomponent {
    fun repositorySubcomponent(): ActorSubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}