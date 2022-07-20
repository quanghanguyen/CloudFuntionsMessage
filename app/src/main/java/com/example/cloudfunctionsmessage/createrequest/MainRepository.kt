package com.example.cloudfunctionsmessage.createrequest

import com.example.cloudfunctionsmessage.firebaseconnection.AuthConnection.uid
import com.example.cloudfunctionsmessage.model.NotificationTokens
import com.example.cloudfunctionsmessage.model.TitleModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class MainRepository @Inject constructor(private val firebaseDatabase : FirebaseDatabase) {
    fun save(
        user_uid : String,
        notificationTokens : NotificationTokens,
        title : String,
        author : String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val data = TitleModel(user_uid, notificationTokens, title, author)
        if (uid != null) {
            firebaseDatabase.getReference("articles").child(uid).setValue(data)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess(it.toString())
                    }
                    else {
                        onFail(it.exception?.message.orEmpty())
                    }
                }
                .addOnFailureListener {
                    onFail(it.message.orEmpty())
                }
            }
        }
    }