package com.joebruckner.twocan.ui.auth

import android.content.Intent
import com.google.android.gms.auth.UserRecoverableAuthException
import com.joebruckner.twocan.data.AuthManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AuthPresenter(val authManager: AuthManager) :
        AuthContract.Presenter {
    override var view: AuthContract.View? = null

    override fun authorizeGoogle() {
        println("Starting google auth in $view")
        view?.showGoogleLoginFlow(authManager.getGoogleSignInIntent())
    }

    override fun onGoogleAuthResult(data: Intent) {
        authManager.signInWithGoogle(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    view?.showSuccessfulLogin()
                }, { error ->
                    when (error) {
                        is UserRecoverableAuthException -> {
                            view?.showRecoverFlow(error.intent)
                        }
                        else -> {
                            error.printStackTrace()
                            view?.showError("Error logging in")
                            revoke()
                        }
                    }
                })
    }

    override fun revoke() {
        authManager.disconnectGoogle().subscribe { status ->
            println(status.statusMessage)
        }
    }

    override fun authorizeFacebook() {
        throw UnsupportedOperationException()
    }

    override fun attachView(view: AuthContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}