package com.example.a6monthproject.ui.playlist

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a6monthproject.base.BaseViewModel
import com.example.a6monthproject.data.remote.RetrofitClient
import com.example.a6monthproject.model.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel : BaseViewModel() {

    private val apiService = RetrofitClient.create()

    fun getPlaylist(): LiveData<PlayList>{
        val data = MutableLiveData<PlayList>()

        apiService.getPlaylist().enqueue(object : Callback<PlayList>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.errorBody() != null){
                    Log.v("ololo", "onResponse: ${response.body()}")
                }else {
                    data.value = response.body      ()
                }
            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                Log.e("ololo", "onFailure: " + t.message)
            }

        })
        return data

    }
}