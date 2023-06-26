package com.geekbrains.poplibrary.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentUserInfoBinding
import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.geekbrains.poplibrary.mvp.presenter.UserInfoPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    private var _binding : FragmentUserInfoBinding ?= null
    private val binding
        get() = _binding!!

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter(App.instance.router)
    }

    companion object {
        const val GIT_USER = "user"

        fun newInstance(bundle: Bundle) : UserInfoFragment {
            val fragment = UserInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.user = arguments?.getParcelable(GIT_USER,
            GithubUser::class.java)
    }

    override fun setUserLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}