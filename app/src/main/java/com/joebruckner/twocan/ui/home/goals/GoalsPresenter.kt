package com.joebruckner.twocan.ui.home.goals

class GoalsPresenter : GoalsContract.Presenter {
    override var view: GoalsContract.View? = null

    override fun attachView(view: GoalsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}