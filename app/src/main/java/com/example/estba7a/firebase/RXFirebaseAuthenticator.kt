package com.example.estba7a.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Completable
import io.reactivex.Single

class RXFirebaseAuthenticator {

    private val authenticator = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(email: String, password: String): Single<FirebaseUser> {
        return Single.create<FirebaseUser> { emitter ->
            authenticator.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        authenticator.currentUser?.let { currentUser -> emitter.onSuccess(currentUser) }
                    } else {
                        emitter.onError(task.exception ?: Throwable("failed to create new user"))
                    }
                }
        }

    }

    fun sendVerificationCode(): Completable {

        return Completable.create {
            if(authenticator.currentUser?.isEmailVerified == true)
                it.onComplete()
            authenticator.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful)
                    it.onComplete()
                else
                    it.onError(Throwable(task.exception))
            }
        }
    }

    fun signInWithEmailAndPassword(email: String,password: String): Single<FirebaseUser>{
        return Single.create {emitter->
            authenticator.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if(task.isSuccessful)
                        authenticator.currentUser?.let { emitter.onSuccess(it) }
                    else
                        emitter.onError(Throwable(task.exception))
                }
        }
    }


}