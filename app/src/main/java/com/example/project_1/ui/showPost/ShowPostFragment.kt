package com.example.project_1.ui.showPost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.ui.viewUser.ViewUserDetailFragment
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.showPost.adapter.PostAdapter

class ShowPostFragment : Fragment() {
    private val postAdapter : PostAdapter by lazy {
        PostAdapter()
    }
    private lateinit var showPostViewModel : ShowPostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_post, container, false)
        val homeButtonItemPost: Button = view.findViewById(R.id.btnHome)
        view.findViewById<RecyclerView>(R.id.recyclerViewLoadImage).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }
        showPostViewModel = ViewModelProvider(this).get(ShowPostViewModel::class.java)
        fetchPost()

        homeButtonItemPost.setOnClickListener{
           val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ViewUserDetailFragment())
        }
        return view
    }

    private fun fetchPost() {
        showPostViewModel.fetchPosts(
            onResult= { showPostData ->
                postAdapter.updateData(showPostData)
            }
        )
    }
}