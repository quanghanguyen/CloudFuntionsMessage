package com.example.cloudfunctionsmessage.list.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) : ViewModel() {

    var saveResult = MutableLiveData<SaveResult>()

    sealed class SaveResult {
        class ResultOk(val successMessage : String) : SaveResult()
        class ResultError(val errorMessage : String) : SaveResult()
    }

    fun save(
        user_uid : String,
        liked : String,
        notificationTokens : String,
        author : String,
    ) {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            detailsRepository.like(user_uid, liked, notificationTokens, author, {
                saveResult.value = SaveResult.ResultOk("Success")
            }, {
                saveResult.value = SaveResult.ResultOk(it)
            })
        }
    }
}