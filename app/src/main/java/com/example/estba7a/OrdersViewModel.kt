package com.example.estba7a

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.estba7a.firebase.Repository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrdersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val orders  = MutableLiveData<List<Order?>>()

    init {
        getOrdersForToday()
    }

    private fun getOrdersForToday() =
        repository.getAllOrdersForDay("2019-04-07")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(Schedulers.io())
            ?.subscribe({
                orders.postValue(it ?: listOf())
            },{
                Log.e("error in viewModel",it.toString())
            })

}