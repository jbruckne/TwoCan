package com.joebruckner.twocan.ui.common

class BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(error: String)
    }

    interface Presenter<V: View> {
        fun attachView(view: V)
        fun detachView()
    }
}