package com.example.estba7a

import android.app.Application
import com.google.firebase.FirebaseApp

class Estba7a : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}