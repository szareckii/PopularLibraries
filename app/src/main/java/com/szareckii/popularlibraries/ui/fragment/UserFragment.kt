package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.databinding.FragmentUserBinding
import com.szareckii.popularlibraries.mvp.model.api.ApiHolder
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.RepositoryRvAdapter
import com.szareckii.popularlibraries.ui.adapter.UsersRvAdapter
import com.szareckii.popularlibraries.ui.image.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.item_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
            }
        }
    }

    val presenter by moxyPresenter {
        val user = arguments?.get("user")
        RepositoryPresenter(App.instance.router, user as GithubUser,  RetrofitGithubUsersRepo(ApiHolder.api), AndroidSchedulers.mainThread())
//        UserPresenter(App.instance.router, user as GithubUser)
    }

    private var adapter: RepositoryRvAdapter? = null

    private var _binding: FragmentUserBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.rvRepository.layoutManager = LinearLayoutManager(requireContext())
        adapter = RepositoryRvAdapter(presenter.repositoryListPresenter)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        binding.rvRepository.addItemDecoration(dividerItemDecoration)

        binding.rvRepository.adapter = adapter
    }

    override fun setUserLogin(text: String) {
        binding.loginUser.text = text
    }

    override fun updateUserReposList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backClick()
}