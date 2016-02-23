package com.joebruckner.twocan.ui.auth


import android.os.Bundle
import com.joebruckner.twocan.R
import com.joebruckner.twocan.ui.common.BaseFragment
import com.joebruckner.twocan.ui.home.MainActivity
import com.joebruckner.twocan.util.snack
import com.pawegio.kandroid.startActivity
import com.pawegio.kandroid.toast

class AuthFragment : BaseFragment(), AuthContract.View {
    override val layoutId = R.layout.fragment_auth

    val presenter = AuthPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
    }

    override fun showSuccessfulLogin() {
        toast("Successfully logged in")
        parent.startActivity<MainActivity>()
    }

    override fun showLoading() {
        throw UnsupportedOperationException()
    }

    override fun hideLoading() {
        throw UnsupportedOperationException()
    }

    override fun showError(error: String) {
        view?.snack(error, "retry") {
            presenter.simpleAuth("Test")
        }
    }
}
