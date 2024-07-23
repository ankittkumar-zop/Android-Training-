// ShowPostFragment.kt
package com.example.project_1.ui.showPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.showPost.adapter.PostAdapter
import com.example.project_1.ui.viewUser.ViewUserDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowPostFragment : Fragment() {

    private lateinit var rvShowPost: RecyclerView
    private val showPostViewModel: ShowPostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvShowPost = view.findViewById<RecyclerView>(R.id.recyclerViewLoadImage).apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        val postAdapter = PostAdapter(requireContext(), emptyList()) { postId ->
            showPostViewModel.toggleLike(postId)
        }

        rvShowPost.adapter = postAdapter

        showPostViewModel.posts.observe(viewLifecycleOwner) { posts ->
            val updatedPosts = posts.filter { it.title != null && it.url != null }
            postAdapter.updateData(updatedPosts)
        }

        val homeButtonItemPost: Button = view.findViewById(R.id.btnHome)
        homeButtonItemPost.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ViewUserDetailFragment())
        }

        lifecycleScope.launch {
            showPostViewModel.fetchPost()
        }
    }
}
