package com.geekbrains.poplibrary.ui.activity

import android.os.Bundle

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.R
import com.geekbrains.poplibrary.mvp.presenter.MainPresenter
import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.mvp.view.rxjavatest.Creation
import com.geekbrains.poplibrary.mvp.view.rxjavatest.Operators
import com.geekbrains.poplibrary.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder

import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainViewImpl {

    private var vb: ActivityMainBinding? = null

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        App.instance.appComponent.inject(this)
        //Creation().exec()
        //Operators().exec()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigatorHolder.removeNavigator()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        //super.onBackPressed()
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}