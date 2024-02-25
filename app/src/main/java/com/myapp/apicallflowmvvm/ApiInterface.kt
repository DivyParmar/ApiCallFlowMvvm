package com.myapp.apicallflowmvvm

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/v2/everything?q=IT&form=2023-05-01&sortby=publishedAt&apiKey=a6dcb214eb204e61ab5cea48eefcc62d")
    suspend fun getNews():Response<JsonObject>

}