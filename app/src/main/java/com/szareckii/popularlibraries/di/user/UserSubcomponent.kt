package com.szareckii.popularlibraries.di.user

import com.szareckii.popularlibraries.di.UserScope
import com.szareckii.popularlibraries.di.repository.RepositorySubcomponent
import com.szareckii.popularlibraries.di.repository.module.RepositoryModule
import com.szareckii.popularlibraries.di.user.module.UserModule
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
        modules = [
            UserModule::class
        ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRvAdapter: UsersRvAdapter)
}