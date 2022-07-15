package com.example.cloudfunctionsmessage.list

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudfunctionsmessage.model.TitleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {
    val loadList = MutableLiveData<LoadList>()

    sealed class LoadList {
        class ResultOk(val list : ArrayList<TitleModel>) : LoadList()
        class ResultError(val errorMessage: String) : LoadList()
    }

    fun load() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            listRepository.loadList({
                loadList.value = LoadList.ResultOk(it)
            }, {
                loadList.value = LoadList.ResultError(it)
            })
        }
    }
}