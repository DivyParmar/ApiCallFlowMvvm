package com.myapp.apicallflowmvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class ApiViewModel(application: Application) : AndroidViewModel(application) {

    var newsData = MutableLiveData<Response<JsonObject>>()

    fun getNews() {
        viewModelScope.launch {
            newsData.postValue(ApiRepository.getNews())
//            ApiRepository.getNews()
        }
    }
}