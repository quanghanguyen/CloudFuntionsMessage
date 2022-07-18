package com.example.cloudfunctionsmessage.signin

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignInRepository @Inject constructor(private val firebaseAuth: FirebaseAuth){
    fun signUp(
        email : String,
        password : String,
        onSuccess: (AuthResult) -> Unit,
        onFail: (String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess(it.result)
                } else {
                    onFail(it.exception?.message.orEmpty())
                }
            }
            .addOnFailureListener {
                onFail(it.message.orEmpty())
            }
        }
    }