package com.example.estba7a

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.simpleFormat(dateFormat : String? = "yyyy-MM-dd") : String{
    val myFormat = SimpleDateFormat(dateFormat ?: "yyyy-MM-dd")
    return myFormat.format(this)
}