package com.example.estba7a.firebase

import android.provider.ContactsContract
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Completable
import io.reactivex.Observable

class RXFirebaseDatabase {

    fun setValue(ref : DatabaseReference , obj : Any): Completable{
        return Completable.create{emitter ->
            ref.setValue(obj)
                .addOnCompleteListener {
                    if(it.isSuccessful)
                        emitter.onComplete()
                    else
                        emitter.onError(Throwable(it.exception))
                }
        }
    }

    fun getOrders(ref : DatabaseReference) : Observable<DataSnapshot>{
        return Observable.create<DataSnapshot> {emitter ->
            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(Throwable(p0.toException()))
                }

                override fun onDataChange(p0: DataSnapshot) {
                    emitter.onNext(p0)
                }

            })
        }
    }

}