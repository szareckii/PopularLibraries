package com.szareckii.popularlibraries.di.repository

import com.szareckii.popularlibraries.di.RepositoryScope
import com.szareckii.popularlibraries.di.repository.module.ActorModule
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
        modules = [
            ActorModule::class
        ]
)
interface ActorSubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}