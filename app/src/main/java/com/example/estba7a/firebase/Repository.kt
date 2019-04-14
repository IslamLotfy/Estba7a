package com.example.estba7a.firebase

import com.example.estba7a.Order
import com.example.estba7a.simpleFormat
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(private val database: FirebaseDatabase) {
    private val databaseRef = database.reference
    private val rxFirebaseDatabase = RXFirebaseDatabase()
    private val ordersNode = "orders"
    private val usersNode = "users"
    private val itemsNode = "items"
    private val typesNode = "types"

    fun getAllOrdersForDay(date: String = Date().simpleFormat()): Observable<List<Order?>>? {
        val nodeRef = databaseRef.child(ordersNode).child(date)
        return Observable.concatArray(rxFirebaseDatabase.getValues(nodeRef))
            .map { it.children.map { child -> child.getValue(Order::class.java) } }
    }

    fun getAllItems(): Observable<List<String?>>? {
        val nodeRef = databaseRef.child(itemsNode)
        return Observable.concatArray(rxFirebaseDatabase.getValues(nodeRef))
            .map { it.children.map { child -> child.getValue(String::class.java) } }
    }

    fun getAllTypes(): Observable<List<String?>>? {
        val nodeRef = databaseRef.child(typesNode)
        return Observable.concatArray(rxFirebaseDatabase.getValues(nodeRef))
            .map { it.children.map { child -> child.getValue(String::class.java) } }
    }

    fun getAllOrdersForUsers(userKey: String): Observable<List<Order?>>? {
        val nodeRef = databaseRef.child(usersNode).child(userKey).child(ordersNode)
        return Observable.concatArray(rxFirebaseDatabase.getValues(nodeRef))
            .map { it.children.map { child -> child.getValue(Order::class.java) } }
    }

    fun getOrderForUserForDay(userKey: String, date: String = Date().simpleFormat()): Observable<Order?>? {
        val nodeRef = databaseRef.child(usersNode).child(userKey).child(ordersNode).child(date)
        return Observable.concatArray(rxFirebaseDatabase.getValues(nodeRef))
            .map { it.getValue(Order::class.java) }
    }

    fun addOrder(userKey: String, date: String = Date().simpleFormat(), order: Order): Completable {
        val userNodeRef = databaseRef.child(usersNode).child(userKey).child(ordersNode).child(date)
        val orderNodeRed = databaseRef.child(ordersNode).child(date)
        return Completable.concatArray(
            rxFirebaseDatabase.setValue(userNodeRef, order),
            rxFirebaseDatabase.setValue(orderNodeRed, order)
        )
    }

}
