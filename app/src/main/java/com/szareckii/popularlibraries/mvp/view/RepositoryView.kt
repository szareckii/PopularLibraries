package com.szareckii.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepositoryView: MvpView {
    fun setLogin(text: String)
    fun setTitle(text: String)
    fun setForksCount(text: String)
}