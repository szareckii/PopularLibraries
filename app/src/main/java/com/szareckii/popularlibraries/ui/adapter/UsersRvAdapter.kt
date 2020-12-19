package com.szareckii.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import com.szareckii.popularlibraries.mvp.presenter.list.IUserListPresenter
import com.szareckii.popularlibraries.mvp.view.listUsers.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*
import javax.inject.Inject

class UsersRvAdapter(val presenter: IUserListPresenter): RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener{ presenter.itemClickListener?.invoke(holder)}
        presenter.bindView(holder)
    }

    override fun getItemCount() =  presenter.getCount()

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), UserItemView, LayoutContainer{
        override var pos = -1

        override fun setLogin(text: String) {
            tv_login.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, iv_image)
        }
    }

}