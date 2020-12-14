package com.szareckii.popularlibraries.di

import com.szareckii.popularlibraries.di.modules.*
import com.szareckii.popularlibraries.mvp.presenter.MainPresenter
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.ui.activity.MainActivity
import com.szareckii.popularlibraries.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApiModule::class,
    AppModule::class,
    CacheModule::class,
    ImageModule::class,
    NavigationModule::class,
    RepoModule::class
])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)

    //вот этого здесь не будет
    fun inject(usersFragment: UsersFragment)

}