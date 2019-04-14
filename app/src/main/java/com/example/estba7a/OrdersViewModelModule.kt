package com.example.estba7a

import androidx.lifecycle.ViewModel
import com.example.estba7a.DI.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class OrdersViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun provideViewModel(viewModel: OrdersViewModel): ViewModel
}