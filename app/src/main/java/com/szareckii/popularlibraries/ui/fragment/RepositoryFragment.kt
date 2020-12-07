package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.szareckii.popularlibraries.databinding.FragmentRepositoryBinding
import com.szareckii.popularlibraries.mvp.model.api.ApiHolder
import com.szareckii.popularlibraries.mvp.model.entity.GithubUser
import com.szareckii.popularlibraries.mvp.model.entity.GithubRepository
import com.szareckii.popularlibraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.szareckii.popularlibraries.mvp.presenter.RepositoryPresenter
import com.szareckii.popularlibraries.mvp.view.RepositoryView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Router

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    companion object {
        private const val REPOSITORY_ARG = "repository"
        private const val USER_ARG = "user"
        fun newInstance(user: GithubUser, repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val user = arguments?.get(USER_ARG) as GithubUser
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY_ARG) as GithubRepository
        RepositoryPresenter(App.instance.router, user, repository)
    }

    private var _binding: FragmentRepositoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): LinearLayout? {
        _binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setLogin(text: String) {
        _binding?.loginUser?.text = text
    }

    override fun setTitle(text: String) {
        _binding?.nameRepository?.text = text
    }

    override fun setForksCount(text: String) {
        _binding?.numberOfForks?.text = text
    }

    override fun backPressed() = presenter.backClick()
}