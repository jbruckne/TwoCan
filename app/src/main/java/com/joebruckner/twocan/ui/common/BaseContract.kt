package com.joebruckner.twocan.ui.common

class BaseContract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(error: String)
    }

    interface Presenter<V: View> {
        var view: V?
        fun attachView(view: V)
        fun detachView()
    }
}