package com.joebruckner.twocan.di

import android.app.Activity
import com.firebase.client.Firebase
import com.joebruckner.twocan.data.AuthManager
import com.joebruckner.twocan.data.FirebaseAuthManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {
    @Provides @PerActivity
    fun provideActivity(): Activity = activity

    @Provides @PerActivity
    fun provideFirebaseRef(): Firebase {
        return Firebase("https://two-can.firebaseio.com/")
    }

    @Provides @PerActivity
    fun provideAuthManager(ref: Firebase, activity: Activity): AuthManager {
        return FirebaseAuthManager(ref, activity)
    }
}