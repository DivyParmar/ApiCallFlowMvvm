package com.myapp.apicallflowmvvm

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    init {
        retrofit = Retrofit.Builder().client(provideOKHttpClient())
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        var retrofit: Retrofit? = null
        var mInstance: RetrofitClient? = null

        fun getInstance(): RetrofitClient{
            if (mInstance == null){
                mInstance = RetrofitClient()
            }
            return mInstance!!
        }

    }

    fun getApi(): ApiInterface{
        return retrofit?.create(ApiInterface::class.java)!!
    }

    private fun provideAuthInterceptor():Interceptor{
        return Interceptor { chain->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDAyZjE4MGZjMzJhZjVlZTdlOWRjYjViZjRlOTM3NTMzMmEzMTY5MThiOWNiMjdiN2I2YWQ3ZTgyNDljMTZiYWY4NTA5YTc3NTAzMzZhOTAiLCJpYXQiOjE2NTgzMjg5MjQuNDI3Mjc4OTk1NTEzOTE2MDE1NjI1LCJuYmYiOjE2NTgzMjg5MjQuNDI3Mjc5OTQ5MTg4MjMyNDIxODc1LCJleHAiOjE2ODk4NjQ5MjQuNDI0NzY3OTcxMDM4ODE4MzU5Mzc1LCJzdWIiOiI0MjM1OTMiLCJzY29wZXMiOltdfQ.SE7sA3GsA6zvDcqztdthJJz3bYoZcnmBUyGeVPDfoIgfBAt6uD-wFNwCDJ2PGQpomJxISX05GLBmrOxPPYf46ZTqvY632aexlsxsevY2l0HocuMHf1W1McncXSf3x9nxNo8fr95k5yE1SGJZZXc6zyLVp733QAdKcfnJ7Iszo_GX6s8YAM56UCQW2rSOSfGgvIW5uuiKxEWv-HnJAglk9JcUswzAYnErZsmQ5fQdeMk4pOwnsmlHf5U0jd29utECyGHTUVx3umquBnAiZe63Ujwe0eD0lAAJo3zJNCkHPSFDTXjN_MKqqkaG-wz53M7W2mSd4jTapwu3xXzWRsRfaDHW38MkEvmGo42YUVE6z-mECkRt0KaWGB8SWo4IJ8Nci3Gylsf8VcnMo5oY3OBOgWmZrmlK_Oj2bdXsJOENOX1if8ECfysCaumJFc_XBHKGaVYXQphgopL1NZ3llyVa6bMx-U9G8VLSE_NG-HYXhevOtO3NH4Bp0e5p4hcHmj0AWkxoxzj7XI7O-JzmJUKfnGscUEE0xl4vI_H_kYX4aXWIcg0QO9NldkuGNIQJtV-oLSyI5XKfeuX_YVjG-osCiGeGYYcMBN1_HFOuPXYjYUDOo2ONgr0dL2k7n1n3y-F-pI7pcXbZNKDdFarTrB10QtqOhhN5LFL1SGNattMsSEk")
                    .build()
                chain.proceed(newRequest)
        }
    }

    private fun provideOKHttpClient():OkHttpClient{
        return OkHttpClient.Builder().connectTimeout(500L,TimeUnit.MILLISECONDS).addInterceptor(provideAuthInterceptor()).build()
    }
}