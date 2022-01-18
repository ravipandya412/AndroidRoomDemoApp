package com.example.androidroomdemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidroomdemoapp.db.Subscriber
import com.example.androidroomdemoapp.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository):ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        insertSubscriber(Subscriber(0, name, email))
        inputName.value = ""
        inputEmail.value = ""
    }

    private fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        repository.insert(subscriber)
    }

}