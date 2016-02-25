package com.joebruckner.twocan.di

import android.app.Activity
import com.firebase.client.Firebase
import com.joebruckner.twocan.data.AuthManager
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {
    fun activity(): Activity
    fun firebaseRef(): Firebase
    fun authManager(): AuthManager
}