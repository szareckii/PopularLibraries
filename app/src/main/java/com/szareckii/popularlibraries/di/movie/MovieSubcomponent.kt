package com.szareckii.popularlibraries.di.movie

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.di.repository.RepositorySubcomponent
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
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}