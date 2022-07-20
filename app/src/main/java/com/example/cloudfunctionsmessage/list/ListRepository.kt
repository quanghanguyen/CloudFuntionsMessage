package com.example.cloudfunctionsmessage.list

import android.util.Log
import com.example.cloudfunctionsmessage.model.TitleModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class ListRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {
    fun loadList(
        onSuccess : (ArrayList<TitleModel>) -> Unit,
        onFail : (String) -> Unit
    ) {
        firebaseDatabase.getReference("articles").addValueEventListener(object:
        ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val list = ArrayList<TitleModel>()
                    for (requestSnapshot in snapshot.children) {
                        requestSnapshot.getValue(TitleModel::class.java)?.let {
                            list.add(0, it)
                        }
                    }
                    onSuccess(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFail(error.message)
            }
        })
    }
}