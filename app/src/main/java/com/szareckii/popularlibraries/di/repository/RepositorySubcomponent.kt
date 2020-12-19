package com.szareckii.popularlibraries.di.repository

import com.szareckii.popularlibraries.di.RepositoryScope
import com.szareckii.popularlibraries.di.repository.module.RepositoryModule
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
        modules = [
            RepositoryModule::class
        ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}