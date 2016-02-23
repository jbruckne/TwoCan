package com.joebruckner.twocan.di

import android.content.Context
import com.joebruckner.twocan.TwoCanApp
import com.joebruckner.twocan.ui.home.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AppModule::class)
)
interface AppComponent {
    fun inject(app: TwoCanApp)
    fun inject(mainActivity: MainActivity)

    fun context(): Context
}