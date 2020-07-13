package com.example.ys.know

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi

import retrofit2.Call

import retrofit2.Callback
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class KnowApi {
    private var knowInterface:KnowInterface
    init {
        val okHttpClient = OkHttpClient.Builder().build()
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://script.google.com/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(okHttpClient)
                .build()
        knowInterface = retrofit.create(KnowInterface::class.java)
    }
    fun getKnowList(callback: (List<KnowData>) -> Unit) {
        knowInterface.know().enqueue(object : Callback<List<KnowData>> {

            override fun onResponse(call: Call<List<KnowData>>?, response: Response<List<KnowData>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Log.d("response ok", it.toString())
                            callback(it)
                        }
                    }else{
                        Log.d("api", "failed 1")
                    }
                }
            }

            override fun onFailure(call: Call<List<KnowData>>?, t: Throwable?) {
                Log.d("api", "failed 2")
                Log.d("api", t.toString())
            }
        })
    }
}