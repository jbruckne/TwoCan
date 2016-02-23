package com.joebruckner.twocan.ui.auth

import android.os.Bundle
import com.joebruckner.twocan.R
import com.joebruckner.twocan.ui.common.BaseActivity

class AuthActivity : BaseActivity() {
    override val menuId = R.menu.menu_auth
    override val layoutId = R.layout.activity_auth

    override fun inject(item: Any) {
        throw UnsupportedOperationException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
