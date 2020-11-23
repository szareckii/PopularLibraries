package com.szareckii.popularlibraries.mvp.presenter.list

import com.szareckii.popularlibraries.mvp.view.list.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V)-> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}