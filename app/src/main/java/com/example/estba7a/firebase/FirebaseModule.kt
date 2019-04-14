package com.example.estba7a.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {
    @Provides
    fun provideFirebaseAuthenticator() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

}