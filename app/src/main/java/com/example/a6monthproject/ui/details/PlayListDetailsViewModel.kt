package com.example.a6monthproject.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.a6monthproject.base.BaseViewModel
import com.example.a6monthproject.data.remote.RetrofitClient
import com.example.a6monthproject.model.PlayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListDetailsViewModel: BaseViewModel() {



    private val apiService by lazy {
        RetrofitClient.create()
    }

    fun getPlayListDetails(playlistId : String): LiveData<PlayList> {

        val data = MutableLiveData<PlayList>()
        apiService.getPlaylistDetails(playlistId = playlistId).enqueue(object : Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                Log.e("ololo", "onFailure: " + t.message)
            }

        })
        return data
    }


}