package com.joebruckner.twocan.ui.auth

import com.joebruckner.twocan.data.AuthManager
import com.joebruckner.twocan.di.PerActivity
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides @PerActivity
    fun provideAuthPresenter(authManager: AuthManager): AuthContract.Presenter {
        return AuthPresenter(authManager)
    }
}