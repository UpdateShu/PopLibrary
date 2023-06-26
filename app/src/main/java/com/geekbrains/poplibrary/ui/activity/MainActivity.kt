package com.geekbrains.poplibrary.ui.activity

import android.os.Bundle
import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.R
import com.geekbrains.poplibrary.mvp.presenter.MainPresenter
import com.geekbrains.poplibrary.databinding.ActivityMainBinding
import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.navigation.AndroidScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainViewImpl {

    private var vb: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, App.instance.screens)
    }

    val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        App.instance.navigatorHolder.removeNavigator()
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