package com.example.estba7a

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.estba7a.firebase.RXFirebaseAuthenticator
import com.example.estba7a.firebase.RXFirebaseDatabase
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val firebaseAuthenticator = RXFirebaseAuthenticator()
    private val firebaseDatabase = RXFirebaseDatabase()


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val databaseRef = FirebaseDatabase.getInstance().reference
            .child("usres")
            .child("1No8hjLfLxc4JUCjJvrH9ZhHTGH2")
            .child("orders")

        firebaseDatabase.getOrders(databaseRef)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.children.map { it.getValue(Order::class.java) } }
            .subscribe({

                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                Log.d("response",it.toString())
            }, {
                Toast.makeText(this, it.stackTrace.toString(), Toast.LENGTH_SHORT).show()
                Log.e("error",it.toString())
            })
//        firebaseAuthenticator.createUserWithEmailAndPassword("islam.m7md.lotfy@gmail.com","111111")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Toast.makeText(this, it.email ,Toast.LENGTH_SHORT).show()
//            },{
//                Toast.makeText(this,it.stackTrace.toString() ,Toast.LENGTH_SHORT).show()
//            })
//        firebaseAuthenticator.sendVerificationCode()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Toast.makeText(this, "sent " ,Toast.LENGTH_SHORT).show()
//            },{
//                Toast.makeText(this,it.stackTrace.toString() ,Toast.LENGTH_SHORT).show()
//            })
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds orders to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

