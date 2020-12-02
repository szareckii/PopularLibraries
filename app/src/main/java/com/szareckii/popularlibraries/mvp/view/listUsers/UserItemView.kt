package com.szareckii.popularlibraries.mvp.view.listUsers

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadImage(url: String)
}