package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.databinding.FragmentUserBinding
import com.szareckii.popularlibraries.mvp.model.entity.IMDBMovie
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.RepositoryRvAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: IMDBMovie) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private var adapter: RepositoryRvAdapter? = null

    val presenter: UserPresenter by moxyPresenter {
        App.instance.initRepositorySubcomponent()

        val user = arguments?.getParcelable<IMDBMovie>(USER_ARG) as IMDBMovie
        UserPresenter(user).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    private var _binding: FragmentUserBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): LinearLayout? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        _binding?.rvRepository?.layoutManager = LinearLayoutManager(requireContext())
        adapter = RepositoryRvAdapter(presenter.repositoryListPresenter)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        _binding?.rvRepository?.addItemDecoration(dividerItemDecoration)

        _binding?.rvRepository?.adapter = adapter
    }

    override fun setUserLogin(text: String) {
        _binding?.loginUser?.text = text
    }

    override fun updateUserReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backClick()

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseRepositorySubcomponent()
    }
}