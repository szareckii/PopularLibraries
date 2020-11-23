package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.szareckii.popularlibraries.databinding.FragmentUsersBinding
import com.szareckii.popularlibraries.mvp.model.repo.GithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.UsersPresenter
import com.szareckii.popularlibraries.mvp.view.UsersView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter by moxyPresenter {
        UsersPresenter(App.instance.router, GithubUsersRepo())
    }

    val adapter by lazy {
        UsersRvAdapter(presenter.userListPresenter)
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.adapter = adapter
    }

    override fun updateUsersList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}