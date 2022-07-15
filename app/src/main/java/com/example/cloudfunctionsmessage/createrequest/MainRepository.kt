package com.example.cloudfunctionsmessage.createrequest

import com.example.cloudfunctionsmessage.model.TitleModel
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class MainRepository @Inject constructor(private val firebaseDatabase : FirebaseDatabase) {
    fun save(
        token : String,
        title : String,
        author : String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val data = TitleModel(token, title, author)
        firebaseDatabase.getReference("articles").child(token).setValue(data)
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