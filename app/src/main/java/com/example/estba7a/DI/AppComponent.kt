package com.example.estba7a.DI

import android.app.Application
import com.example.estba7a.Estba7a
import com.example.estba7a.firebase.FirebaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    FirebaseModule::class])
interface AppComponent {
    fun inject(app: Estba7a)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}