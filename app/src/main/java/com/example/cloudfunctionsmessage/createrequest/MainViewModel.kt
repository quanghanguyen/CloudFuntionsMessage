package com.example.cloudfunctionsmessage.createrequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudfunctionsmessage.model.NotificationTokens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val saveResult = MutableLiveData<SaveResult>()

    sealed class SaveResult {
        class ResultOk(val successMessage : String): SaveResult()
        class ResultError(val errorMessage : String) : SaveResult()
    }

    fun save(user_uid : String, notificationTokens: NotificationTokens, title : String, author : String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            mainRepository.save(user_uid, notificationTokens, title, author, onSuccess = {
                saveResult.value = SaveResult.ResultOk("Success")
            }, onFail = {
                saveResult.value = SaveResult.ResultOk(it)
            })
        }
    }
}