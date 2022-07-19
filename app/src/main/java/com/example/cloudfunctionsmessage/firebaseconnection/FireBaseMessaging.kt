package com.example.cloudfunctionsmessage.firebaseconnection

import android.content.ContentValues
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

object FireBaseMessaging {
    val firebaseMessaging = FirebaseMessaging.getInstance()

//    fun getToken() {
//        FireBaseMessaging.firebaseMessaging.token.addOnCompleteListener(
//            OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.e(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
//                    return@OnCompleteListener
//                }
//                val token = task.result
//            })
//        }
}