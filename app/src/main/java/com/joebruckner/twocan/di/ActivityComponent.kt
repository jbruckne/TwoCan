package com.joebruckner.twocan.di

import android.app.Activity
import android.view.LayoutInflater
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class)
)
interface ActivityComponent {

    fun activity(): Activity
    fun layoutInflater(): LayoutInflater
}