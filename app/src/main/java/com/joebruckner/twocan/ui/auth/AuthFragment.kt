package com.joebruckner.twocan.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.gms.common.SignInButton
import com.joebruckner.twocan.R
import com.joebruckner.twocan.ui.common.BaseFragment
import com.joebruckner.twocan.ui.home.MainActivity
import com.joebruckner.twocan.util.snack
import com.pawegio.kandroid.find
import com.pawegio.kandroid.startActivity
import com.pawegio.kandroid.toast
import javax.inject.Inject

class AuthFragment : BaseFragment(),
        AuthContract.View {
    override val layoutId = R.layout.fragment_auth
    @Inject lateinit var presenter: AuthContract.Presenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        parent.inject(this)

        val button = view?.find<SignInButton>(R.id.sign_in_button)
        button?.setOnClickListener {
            presenter.authorizeGoogle()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data);

        when (requestCode) {
            AuthActivity.RC_SIGN_IN -> presenter.onGoogleAuthResult(data)
            else -> println(data.extras.keySet())
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onPause() {
        presenter.detachView()
        super.onPause()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                presenter.revoke()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showSuccessfulLogin() {
        toast("Successfully logged in")
        parent.startActivity<MainActivity>()
    }

    override fun showGoogleLoginFlow(intent: Intent) {
        startActivityForResult(intent, AuthActivity.RC_SIGN_IN);
    }

    override fun showRecoverFlow(intent: Intent) {
        startActivityForResult(intent, AuthActivity.RC_RECOVER)
    }

    override fun showError(error: String) {
        view?.snack(error, "retry") {
            presenter.authorizeGoogle()
        }
    }
}