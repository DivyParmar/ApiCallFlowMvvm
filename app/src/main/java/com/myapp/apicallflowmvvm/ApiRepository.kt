package com.myapp.apicallflowmvvm

import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ApiRepository {
    val api = RetrofitClient.getInstance().getApi()

    suspend fun getNews(): Response<JsonObject> {
        return api.getNews()
    }
}