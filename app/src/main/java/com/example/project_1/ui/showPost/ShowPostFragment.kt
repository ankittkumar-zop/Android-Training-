package com.example.project_1.ui.showPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.data.remote.showPost.ShowPostData
import com.example.project_1.data.remote.showPost.ShowPostRetrofitObject
import com.example.project_1.ui.showPost.adapter.PostAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowPostFragment : Fragment() {

    private lateinit var postAdapter : PostAdapter
    private lateinit var rvShowPost : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_post, container, false)

        rvShowPost = view.findViewById(R.id.recyclerViewLoadImage)
        rvShowPost.layoutManager = LinearLayoutManager(requireContext())
        postAdapter = PostAdapter(emptyList())
        rvShowPost.adapter= postAdapter

        fetchPost()

        return view
    }

    private fun fetchPost() {
        val inst = ShowPostRetrofitObject().getShowPostRetrofitObject()
        inst.getPost().enqueue(object : Callback<List<ShowPostData>>{
            override fun onResponse(
                call: Call<List<ShowPostData>>,
                response: Response<List<ShowPostData>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { ShowPostData ->
                        postAdapter.updateData(ShowPostData)

                    }
                }
            }

            override fun onFailure(call: Call<List<ShowPostData>>, t: Throwable) {
                Toast.makeText(requireContext(), "Failed to fetch data", Toast.LENGTH_LONG).show()

            }
        })
    }

}