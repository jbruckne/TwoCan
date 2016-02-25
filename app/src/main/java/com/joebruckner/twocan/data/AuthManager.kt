package com.joebruckner.twocan.data

import android.content.Intent
import com.firebase.client.AuthData
import com.google.android.gms.common.api.Status
import rx.Observable

interface AuthManager {
    companion object {
        val GOOGLE = "google"
        val FACEBOOK = "facebook"
        val TWITTER = "twitter"
    }

    fun getGoogleSignInIntent(): Intent
    fun signInWithGoogle(data: Intent): Observable<AuthData>
    fun signOutWithGoogle(): Observable<Status>
    fun disconnectGoogle(): Observable<Status>
    fun signInWithFacebook()
    fun signOutWithFacebook()
    fun disconnectFacebook()
}