package com.joebruckner.twocan.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides @Singleton
    fun provideAppContext(): Context = app
}