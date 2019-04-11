package com.example.estba7a

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["android:text"])
fun setQuantityText(textView: TextView , quantity: Int ){
    textView.text = quantity.toString()
}