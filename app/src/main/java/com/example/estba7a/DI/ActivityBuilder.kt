package com.example.estba7a.DI

import com.example.estba7a.MainActivity
import com.example.estba7a.OrdersViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [OrdersViewModelModule::class])
    internal abstract fun bindMainActivityInjector(): MainActivity
}