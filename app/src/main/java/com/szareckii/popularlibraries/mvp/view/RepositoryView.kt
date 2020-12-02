package com.szareckii.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepositoryView: MvpView {
    fun setLogin(text: String)
    fun setRepository(text: String)
    fun setNumberOfForks(numberOfForks: Int)
}