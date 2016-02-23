package com.joebruckner.twocan.ui.common

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.joebruckner.twocan.R

abstract class BaseFragment : Fragment() {
    val TAG = javaClass.simpleName
    lateinit var parent: BaseActivity

    // Layout ids
    open val menuId: Int = R.menu.menu_empty
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parent = context as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}