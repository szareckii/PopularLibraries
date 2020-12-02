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
import com.szareckii.popularlibraries.databinding.FragmentRepositoryBinding
import com.szareckii.popularlibraries.databinding.FragmentUserBinding
import com.szareckii.popularlibraries.mvp.model.api.ApiHolder
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubUserRepository
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.presenter.UserPresenter
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.RepositoryRvAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser, repository: GithubUserRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable("user", user)
                putParcelable("repository", repository)
            }
        }
    }

    val presenter by moxyPresenter {
        val user = arguments?.get("user")
        val repository = arguments?.get("repository")
        RepositoryPresenter(App.instance.router, user as GithubUser, repository as GithubUserRepository, RetrofitGithubUsersRepo(ApiHolder.api), AndroidSchedulers.mainThread())
    }

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setLogin(text: String) {
        binding.loginUser.text = text
    }

    override fun setRepository(text: String) {
        binding.nameRepository.text = text
    }

    override fun setNumberOfForks(numberOfForks: Int) {
        binding.numberOfForks.text = numberOfForks.toString()
    }

    override fun backPressed() = presenter.backClick()
}