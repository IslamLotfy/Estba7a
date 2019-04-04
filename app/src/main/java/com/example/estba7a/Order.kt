package com.example.estba7a

import com.google.firebase.database.Exclude

data class Order (
    val owner : String  = "" ,
    val date : String = "",
    val items : List<Item> = listOf()){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "owner" to owner,
            "date" to date,
            "items" to items)
    }
}