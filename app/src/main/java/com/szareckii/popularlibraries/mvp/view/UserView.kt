package com.szareckii.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView: MvpView {
    fun setUserLogin(login: String)
}