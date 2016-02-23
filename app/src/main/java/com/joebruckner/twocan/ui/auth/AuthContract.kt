package com.joebruckner.twocan.ui.auth

import com.joebruckner.twocan.ui.common.BaseContract

class AuthContract {

    interface View : BaseContract.View {
        fun showSuccessfulLogin()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun authorizeGoogle()
        fun authorizeFacebook()
        fun simpleAuth(name: String)
    }
}