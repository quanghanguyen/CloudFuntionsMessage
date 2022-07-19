package com.example.cloudfunctionsmessage.list.details

import com.example.cloudfunctionsmessage.firebaseconnection.AuthConnection.uid
import com.example.cloudfunctionsmessage.model.LikeModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class DetailsRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    fun like (
        user_uid : String,
        liked : String,
        notificationTokens : String,
        author : String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val likedData = LikeModel(user_uid, notificationTokens, author)
        if (uid != null) {
            firebaseDatabase.getReference("likers").child(liked).child(uid).setValue(likedData)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onSuccess(it.toString())
                    } else {
                        onFail(it.exception?.message.orEmpty())
                    }
                }
                .addOnFailureListener {
                    onFail(it.message.orEmpty())
                }
            }
        }
    }