package com.example.kotlinthird

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
@GET("photos")
fun getPosts():Call<ArrayList<TodoModel>>
}