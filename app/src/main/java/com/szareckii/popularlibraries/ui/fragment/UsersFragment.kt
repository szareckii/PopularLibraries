package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.databinding.FragmentUsersBinding
import com.szareckii.popularlibraries.mvp.model.cache.image.room.RoomImageCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import com.szareckii.popularlibraries.ui.image.GlideImageLoader
import com.szareckii.popularlibraries.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    @Inject lateinit var database: Database          //убрать тоже
//    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    val presenter: UsersPresenter by moxyPresenter {
        App.component.inject(this) //удалить как глейд сделаю

        UsersPresenter().apply {
            App.component.inject(this)
        }
    }

    private var adapter: UsersRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): RelativeLayout? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        _binding?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRvAdapter(presenter.userListPresenter, //imageLoader
            GlideImageLoader(RoomImageCache(database, App.instance.cacheDir), AndroidNetworkStatus(context!!))
        )
        _binding?.rvUsers?.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        _binding?.rvUsers?.addItemDecoration(dividerItemDecoration)
    }

    private var _binding: FragmentUsersBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}