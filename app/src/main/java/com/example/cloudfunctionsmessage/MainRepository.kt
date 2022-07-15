package com.example.cloudfunctionsmessage

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class MainRepository @Inject constructor(private val firebaseDatabase : FirebaseDatabase) {
    fun save(
        title : String,
        author : String,
        onSuccess : (String) -> Unit,
        onFail : (String) -> Unit
    ) {
        val data = TitleModel(title, author)
        firebaseDatabase.getReference("articles").push().setValue(data)
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