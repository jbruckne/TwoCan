package com.joebruckner.twocan.ui.auth

import android.os.Bundle
import com.joebruckner.twocan.R
import com.joebruckner.twocan.di.ActivityModule
import com.joebruckner.twocan.ui.common.BaseActivity

class AuthActivity : BaseActivity() {
    override val menuId = R.menu.menu_auth
    override val layoutId = R.layout.activity_auth

    lateinit var component: AuthComponent

    companion object {
        final val RC_SIGN_IN = 9001
        final val RC_RECOVER = 9002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
        replaceFrame(R.id.content, AuthFragment(), false)
    }

    fun initializeInjector() {
        component = DaggerAuthComponent.builder()
                .appComponent(app.getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
        component.inject(this)
    }

    override fun inject(item: Any) {
        when (item) {
            is AuthFragment -> component.inject(item)
            else -> throw Exception("$item could not be injected.")
        }
    }
}
