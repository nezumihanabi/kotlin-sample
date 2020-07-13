package com.example.ys.know

import com.google.gson.FieldNamingPolicy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Response

class ServiceGenerator {
    companion object {
        fun createService(): KnowInterface {
            val apiUrl = "http://localhost:3000/"
            val client = builderHttpClient()
            val json = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(json))
                    .build()
            return retrofit.create(KnowInterface::class.java)
        }
        private fun builderHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }

            return client.build()
        }
    }
}