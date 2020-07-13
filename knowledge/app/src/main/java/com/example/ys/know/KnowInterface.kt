package com.example.ys.know

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KnowInterface {
    @GET("macros/s/AKfycbwcbnOMVU51wTBWal2j5wLxdbd6_7Mk9WKhDJsk9kR4spwc-I0/exec")
    fun know() : Call<List<KnowData>>
}