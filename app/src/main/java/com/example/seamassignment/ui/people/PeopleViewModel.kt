package com.example.seamassignment.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeopleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is people Fragment"
    }
    val text: LiveData<String> = _text
}