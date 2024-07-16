package com.example.project_1.ui.showPost

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.project_1.data.remote.showPost.ShowPostData
import com.example.project_1.data.remote.showPost.ShowPostRetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPostViewModel : ViewModel(){

    fun fetchPosts( onResult: (List<ShowPostData>) -> Unit){
        val inst = ShowPostRetrofitObject().getShowPostRetrofitObject()
        inst.getPost().enqueue(object : Callback<List<ShowPostData>>{
            override fun onResponse(
                call: Call<List<ShowPostData>>,
                response: Response<List<ShowPostData>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let{
                        showPostData -> onResult(showPostData)
                    }
                }
            }
            override fun onFailure(call: Call<List<ShowPostData>>, t: Throwable) {
                Log.d("kkk", "failed to fetch data")
            }
        })
    }
}