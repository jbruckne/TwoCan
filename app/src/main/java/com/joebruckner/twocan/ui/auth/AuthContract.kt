package com.joebruckner.twocan.ui.auth

import android.content.Intent
import com.joebruckner.twocan.ui.common.BaseContract

class AuthContract {

    interface View : BaseContract.View {
        fun showSuccessfulLogin()
        fun showGoogleLoginFlow(intent: Intent)
        fun showRecoverFlow(intent: Intent)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun authorizeGoogle()
        fun onGoogleAuthResult(data: Intent)
        fun authorizeFacebook()
        fun revoke()
    }
}