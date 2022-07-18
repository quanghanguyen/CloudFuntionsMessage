package com.example.cloudfunctionsmessage.firebaseconnection

import com.google.firebase.auth.FirebaseAuth

object AuthConnection {
    val uid = FirebaseAuth.getInstance().uid
}