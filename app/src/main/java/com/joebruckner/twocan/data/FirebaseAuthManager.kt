package com.joebruckner.twocan.data

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.firebase.client.AuthData
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.GoogleApiClient
import rx.Observable

class FirebaseAuthManager(val ref: Firebase, val activity: Activity):
        GoogleApiClient.OnConnectionFailedListener {

    private val id = "283117977414-apg7r643phemhdp4tktp82gqitol1clr.apps.googleusercontent.com"

    private val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(id)
            .requestIdToken(id)
            .requestEmail()
            .build()

    private val googleApiClient = GoogleApiClient.Builder(activity)
            .enableAutoManage(activity as FragmentActivity, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, options)
            .build();

    fun getGoogleSignInIntent(): Intent {
        return Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
    }

    fun getTokenFromResult(result: GoogleSignInResult) : Observable<String> {
        return Observable.create<String> ({ subscriber ->
            val account = result.signInAccount!!
            try {
                val token = GoogleAuthUtil.getToken(
                        activity,
                        account.email,
                        "oauth2:${Scopes.PLUS_ME} ${Scopes.EMAIL} ${Scopes.PROFILE}"
                )
                subscriber.onNext(token)
                subscriber.onCompleted()
            } catch (e: UserRecoverableAuthException) {
                subscriber.onError(e)
            }
        })
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        println(result.errorMessage)
    }

    fun authWithToken(token: String, provider: String): Observable<AuthData> {
        return Observable.create<AuthData> ({ subscriber ->
            ref.authWithOAuthToken(provider, token, object: Firebase.AuthResultHandler {
                override fun onAuthenticationError(error: FirebaseError) {
                    subscriber.onError(error.toException())
                }
                override fun onAuthenticated(data: AuthData) {
                    subscriber.onNext(data)
                    subscriber.onCompleted()
                }
            })
        })
    }
}