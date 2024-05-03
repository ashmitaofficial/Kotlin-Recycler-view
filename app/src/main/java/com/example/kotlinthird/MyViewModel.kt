package com.example.kotlinthird

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MyViewModel(private val application: Application) : AndroidViewModel(application) {

    init {
        getData()
    }

    private lateinit var retrofit: Retrofit
    public val myLiveData = MutableLiveData<ArrayList<TodoModel>>()

    public fun getData() {
        retrofit = RetrofitBuilder.build()
        retrofit.create(ApiInterface::class.java).getPosts()
            .enqueue(object : Callback<ArrayList<TodoModel>> {
                override fun onResponse(call: Call<ArrayList<TodoModel>>, response: Response<ArrayList<TodoModel>>
                ) {
                     val List=response.body()
                    myLiveData.postValue(List)
                }

                override fun onFailure(call: Call<ArrayList<TodoModel>>, t: Throwable) {
                    Toast.makeText(application,t.message,Toast.LENGTH_LONG)
                }
            })
    }
}