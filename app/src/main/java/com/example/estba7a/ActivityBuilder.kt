package com.example.estba7a

import com.example.estba7a.firebase.FirebaseModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FirebaseModule::class])
    internal abstract fun bindMainActivityInjector(): MainActivity
}