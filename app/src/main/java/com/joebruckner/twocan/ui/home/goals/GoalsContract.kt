package com.joebruckner.twocan.ui.home.goals

import com.joebruckner.twocan.ui.common.BaseContract

class GoalsContract {

    interface View : BaseContract.View {
        fun showGoalAdded()
        fun showGoalDeleted()
        fun showLogin()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun checkForAuth()
        fun addGoal()
        fun deleteGoal(goalId: String)
    }
}