package com.example.ys.know

import com.squareup.moshi.Json

data class KnowData(@Json(name = "id") val id: String,  @Json(name = "content") val content: String,  @Json(name = "image") val image: String,@Json(name = "category")  val category:String,@Json(name = "insert_date") val insertDate:String,@Json(name = "update_date") val updateDate:String)
