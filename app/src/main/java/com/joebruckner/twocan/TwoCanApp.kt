package com.joebruckner.twocan

import android.app.Application
import com.firebase.client.Firebase
import com.joebruckner.twocan.di.AppComponent
import com.joebruckner.twocan.di.AppModule
import com.joebruckner.twocan.di.DaggerAppComponent

class TwoCanApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Firebase.setAndroidContext(this)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun getAppComponent() = appComponent
}