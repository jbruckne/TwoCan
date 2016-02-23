package com.joebruckner.twocan.ui.common

import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.joebruckner.twocan.R
import com.joebruckner.twocan.TwoCanApp

abstract class BaseActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName
    lateinit var app: TwoCanApp

    // Layout ids
    protected abstract val menuId: Int
    protected abstract val layoutId: Int
    protected open val fabId = R.id.fab
    protected open val toolbarId = R.id.toolbar

    // View components
    lateinit var root: View
    lateinit var toolbar: Toolbar
    var menu: Menu? = null
    var fab: FloatingActionButton? = null

    protected var title: String = " "
    protected var fabIsEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        app = application as TwoCanApp
        root = window.decorView.rootView

        val f = findViewById(fabId)
        fab = if (f != null) f as FloatingActionButton else null
        toolbar = findViewById(toolbarId) as Toolbar

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(menuId, menu)
        this.menu = menu
        return super.onCreateOptionsMenu(menu)
    }

    // Helper functions

    fun getFragment(id: Int) = supportFragmentManager.findFragmentById(id)
    fun versionAtLeast(version: Int) = Build.VERSION.SDK_INT >= version
    fun updateTitle(title: String) {
        this.title = title
    }

    fun replaceFrame(frameId: Int, fragment: Fragment, backstack: Boolean = false) {
        var transaction = supportFragmentManager.beginTransaction().replace(frameId, fragment)
        if (backstack) transaction.addToBackStack(null).commit()
        else transaction.commit()
    }

    fun color(colorRes: Int): Int {
        return if (versionAtLeast(23)) ContextCompat.getColor(this, colorRes)
        else resources.getColor(colorRes)
    }

    fun enableFab() {
        fabIsEnabled = true
        fab?.show(object : FloatingActionButton.OnVisibilityChangedListener() {
            override fun onShown(f: FloatingActionButton?) {
                if (!fabIsEnabled) disableFab()
            }
        })
    }

    fun disableFab() {
        fabIsEnabled = false
        fab?.hide(object : FloatingActionButton.OnVisibilityChangedListener() {
            override fun onHidden(f: FloatingActionButton?) {
                if (fabIsEnabled) enableFab()
            }
        })
    }

    /*
     * Dependency Injection
     */

    abstract fun inject(item: Any)
}