package com.joebruckner.twocan.ui.auth

import com.joebruckner.twocan.di.ActivityComponent
import com.joebruckner.twocan.di.ActivityModule
import com.joebruckner.twocan.di.AppComponent
import com.joebruckner.twocan.di.PerActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class, AuthModule::class)
)
interface AuthComponent: ActivityComponent {
    fun inject(activity: AuthActivity)
    fun inject(fragment: AuthFragment)
}