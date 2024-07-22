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
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.data.remote.RetrofitObject
import com.example.project_1.data.repo.PostRepo
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.showPost.adapter.PostAdapter
import com.example.project_1.ui.viewUser.ViewUserDetailFragment

class ShowPostFragment : Fragment() {

    private lateinit var postAdapter : PostAdapter
    private lateinit var showPostViewModel : ShowPostViewModel
    private lateinit var rvShowPost : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_post, container, false)
        val homeButtonItemPost: Button = view.findViewById(R.id.btnHome)

        rvShowPost = view.findViewById(R.id.recyclerViewLoadImage)
        rvShowPost.layoutManager = LinearLayoutManager(requireContext())
        showPostViewModel = ViewModelProvider(this@ShowPostFragment, ShowPostViewModelFactory(
            PostRepo(RetrofitObject().getRetroFitInstance() , UserDetailDatabase.getDatabase(requireContext()).showPostDao())
        )).get(ShowPostViewModel::class.java)

        postAdapter = PostAdapter(requireContext(),emptyList() , onLikeClick = { postId ->
            showPostViewModel.toggle(postId)
        })
        rvShowPost.adapter= postAdapter

        showPostViewModel.liveData().observe(viewLifecycleOwner){ posts->
            val updatedPost = posts.filter {  it.title != null && it.url != null }
            postAdapter.updateData(updatedPost)
        }

        homeButtonItemPost.setOnClickListener{
           val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ViewUserDetailFragment())
        }
        return view
    }
}