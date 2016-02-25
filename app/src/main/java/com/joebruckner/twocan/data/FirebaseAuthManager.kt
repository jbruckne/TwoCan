package com.joebruckner.twocan.data

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
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
import com.google.android.gms.common.api.Status
import rx.Observable

class FirebaseAuthManager(val ref: Firebase, val activity: Activity): AuthManager,
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

    override fun getGoogleSignInIntent(): Intent {
        return Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
    }

    override fun signInWithGoogle(data: Intent): Observable<AuthData> {
        return Observable.create { subscriber ->
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (!result.isSuccess)
                subscriber.onError(Exception(result.status.statusMessage))
            val token = getTokenFromResult(result)
            authWithToken(token, AuthManager.GOOGLE).subscribe ({ data ->
                subscriber.onNext(data)
                subscriber.onCompleted()
            }, { error ->
                subscriber.onError(error)
            })
        }
    }

    private fun getTokenFromResult(result: GoogleSignInResult): String {
        val account = result.signInAccount!!
        try {
            return GoogleAuthUtil.getToken(
                    activity,
                    account.email,
                    "oauth2:${Scopes.PLUS_ME} ${Scopes.EMAIL} ${Scopes.PROFILE}"
            )
        } catch (e: UserRecoverableAuthException) {
            throw e
        }
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        println(result.errorMessage)
    }

    override fun signOutWithGoogle(): Observable<Status> {
        ref.unauth()
        return Observable.create { subscriber ->
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback { result ->
                if (result.isSuccess) subscriber.onNext(result)
                else subscriber.onError(Exception("Failed to sign out"))
            }
        }
    }

    override fun disconnectGoogle(): Observable<Status> {
        ref.unauth()
        return Observable.create { subscriber ->
            Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback { result ->
                if (result.isSuccess) subscriber.onNext(result)
                else subscriber.onError(Exception("Failed to sign out"))
                subscriber.onCompleted()
            }
        }
    }

    override fun signInWithFacebook() {
        throw UnsupportedOperationException()
    }

    override fun signOutWithFacebook() {
        throw UnsupportedOperationException()
    }

    override fun disconnectFacebook() {
        throw UnsupportedOperationException()
    }

    private fun authWithToken(token: String, provider: String): Observable<AuthData> {
        Log.d("Token[$provider]", token)
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