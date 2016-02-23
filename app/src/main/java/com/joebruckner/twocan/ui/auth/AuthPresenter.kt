package com.joebruckner.twocan.ui.auth

class AuthPresenter : AuthContract.Presenter {
    override var view: AuthContract.View? = null

    override fun authorizeGoogle() {
        throw UnsupportedOperationException()
    }

    override fun authorizeFacebook() {
        throw UnsupportedOperationException()
    }

    override fun simpleAuth(name: String) {
        throw UnsupportedOperationException()
    }

    override fun attachView(view: AuthContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}