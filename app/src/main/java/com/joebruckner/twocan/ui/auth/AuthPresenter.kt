package com.joebruckner.twocan.ui.auth

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.android.gms.auth.api.Auth
import com.joebruckner.twocan.data.FirebaseAuthManager
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AuthPresenter(val authManager: FirebaseAuthManager) :
        AuthContract.Presenter {
    override var view: AuthContract.View? = null

    override fun authorizeGoogle() {
        view?.showGoogleLoginFlow(authManager.getGoogleSignInIntent())
    }

    override fun onGoogleAuthResult(data: Intent, context: Context) {
        val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
        if (result.isSuccess) {
            authManager.getTokenFromResult(result)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ token ->
                authorize(token, "google")
            }, { error ->
                when (error) {
                    is UserRecoverableAuthException -> view?.recoverAuthToken(error.intent)
                    else -> error.printStackTrace()
                }
            })
        }
    }

    private fun authorize(token: String, provider: String) {
        authManager.authWithToken(token, provider)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data ->
                    println(data.toString())
                    view?.showSuccessfulLogin()
                }, { error ->
                    error.printStackTrace()
                })
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