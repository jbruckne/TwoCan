package com.joebruckner.twocan.ui.auth

import android.content.Context
import android.content.Intent
import com.joebruckner.twocan.ui.common.BaseContract

class AuthContract {

    interface View : BaseContract.View {
        fun showSuccessfulLogin()
        fun showGoogleLoginFlow(intent: Intent)
        fun recoverAuthToken(intent: Intent)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun authorizeGoogle()
        fun onGoogleAuthResult(data: Intent, context: Context)
        fun authorizeFacebook()
    }
}