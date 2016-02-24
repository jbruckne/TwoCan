package com.joebruckner.twocan.di

import android.app.Activity
import com.firebase.client.Firebase
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {
    @Provides @PerActivity
    fun provideActivity(): Activity = activity

    @Provides @PerActivity
    fun provideFirebase(): Firebase {
        return Firebase("https://two-can.firebaseio.com/")
    }
}