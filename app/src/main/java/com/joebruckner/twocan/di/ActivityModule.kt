package com.joebruckner.twocan.di

import android.app.Activity
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {

    @Provides @PerActivity
    fun provideActivity(): Activity = activity

    @Provides @PerActivity
    fun provideLayoutInflater(activity: Activity): LayoutInflater {
        return activity.layoutInflater
    }
}