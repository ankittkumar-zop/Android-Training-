package com.example.project_1.ui.showPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.data.remote.RetrofitObject
import com.example.project_1.data.repo.PostRepo
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.showPost.adapter.PostAdapter
import com.example.project_1.ui.viewUser.ViewUserDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowPostFragment : Fragment() {

    private lateinit var rvShowPost: RecyclerView
    private lateinit var showPostViewModel: ShowPostViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.item_post, container, false)
        val homeButtonItemPost: Button = view.findViewById(R.id.btnHome)
        rvShowPost = view.findViewById<RecyclerView>(R.id.recyclerViewLoadImage).apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        val postRepo = PostRepo(
            RetrofitObject.getRetroFitInstance(),
            UserDetailDatabase.getDatabase(requireContext()).showPostDao()
        )

        showPostViewModel = ShowPostViewModel(postRepo)
        postAdapter = PostAdapter(requireContext(), emptyList()) { postId ->
            showPostViewModel.toggleLike(postId)
        }

        rvShowPost.adapter = postAdapter

        showPostViewModel.posts.observe(viewLifecycleOwner) { posts ->
            val updatedPosts = posts.filter { it.title != null && it.url != null }
            postAdapter.updateData(updatedPosts)
        }

        homeButtonItemPost.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ViewUserDetailFragment())
        }

        lifecycleScope.launch {
            showPostViewModel.fetchPost()
        }
        return view
    }
}