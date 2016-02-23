package com.joebruckner.twocan.ui.home

import android.os.Bundle
import com.joebruckner.twocan.R
import com.joebruckner.twocan.ui.common.BaseActivity

class MainActivity : BaseActivity() {
    override val menuId = R.menu.menu_main
    override val layoutId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inject(item: Any) {
        throw UnsupportedOperationException()
    }
}
