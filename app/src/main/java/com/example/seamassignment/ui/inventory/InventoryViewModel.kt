package com.example.seamassignment.ui.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InventoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is inventory Fragment"
    }
    val text: LiveData<String> = _text
}