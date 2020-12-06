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
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.szareckii.popularlibraries.mvp.model.repo.cache.RoomGithubRepositoriesCache
import com.szareckii.popularlibraries.mvp.model.repo.cache.RoomGithubUsersCache
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.UserView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.RepositoryRvAdapter
import com.szareckii.popularlibraries.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    private val cacheRepositories = RoomGithubRepositoriesCache(Database.getInstance())

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(App.instance.router, user,  RetrofitGithubRepositoriesRepo(ApiHolder.api,
            AndroidNetworkStatus(requireContext()), cacheRepositories), AndroidSchedulers.mainThread())
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