package com.example.cloudfunctionsmessage.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository) : ViewModel() {

    var signInResult = MutableLiveData<SignInResult>()

    sealed class SignInResult {
        class ResultOk(val successMessage : String) : SignInResult()
        class ResultError(val errorMessage : String) : SignInResult()
    }

    fun signIn(email : String, password : String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            signInRepository.signUp(email, password, {
                signInResult.value = SignInResult.ResultOk("Success")
            }, {
                signInResult.value = SignInResult.ResultError(it)
            })
        }
    }
}