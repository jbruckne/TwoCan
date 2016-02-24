package com.joebruckner.twocan.ui.auth

import android.app.Activity
import com.firebase.client.Firebase
import com.joebruckner.twocan.data.FirebaseAuthManager
import com.joebruckner.twocan.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides @PerActivity
    fun provideFirebaseAuthManager(ref: Firebase, activity: Activity): FirebaseAuthManager {
        return FirebaseAuthManager(ref, activity)
    }

    @Provides @PerActivity
    fun provideAuthPresenter(authManager: FirebaseAuthManager): AuthPresenter {
        return AuthPresenter(authManager)
    }
}