package com.example.estba7a

import com.google.firebase.database.Exclude

data class Item (
    val quantity : Int = 0,
    val name : String = "",
    val type : String = ""){
    @Exclude
    fun toMap(): Map<String,Any?>{
        return mapOf(
            "quantity" to quantity,
            "name" to name,
            "type" to type
        )
    }
}