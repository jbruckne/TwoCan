package com.joebruckner.twocan.ui.common

class BasePresenter<View: BaseContract.View>() : BaseContract.Presenter<View> {
    var view: View? = null

    override fun attachView(view: View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}