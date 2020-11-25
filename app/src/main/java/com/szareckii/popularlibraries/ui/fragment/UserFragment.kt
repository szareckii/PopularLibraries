package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.szareckii.popularlibraries.databinding.FragmentUserBinding
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
            }
        }
    }

    val presenter by moxyPresenter {
        val user = arguments?.get("user")
        UserPresenter(App.instance.router, user as GithubUser)
    }

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backClick()

    override fun setUserLogin(login: String) {
        binding.loginUser.text = login
    }
}