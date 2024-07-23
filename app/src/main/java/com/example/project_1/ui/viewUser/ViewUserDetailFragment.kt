package com.example.project_1.ui.viewUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.addUser.AddUserDetailFragment
import com.example.project_1.ui.showPost.ShowPostFragment
import com.example.project_1.ui.viewUser.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewUserDetailFragment : Fragment() {

    private val viewUserViewModel: ViewUserViewModel by viewModels()
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUserTv: TextView
    private lateinit var addUserButton: Button
    private lateinit var showPostButton: Button
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_view_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupRecyclerView()
        settingUpClickListener()
        loadUserData()
        observerViewModel()
    }

    private fun observerViewModel() {
        viewUserViewModel.allUsers.observe(viewLifecycleOwner) { users ->
            rvAdapter.updateData(users)
            noUserTv.isVisible = users.isEmpty()
        }
    }

    private fun setupRecyclerView() {
        rvAdapter = UserAdapter(emptyList(), onDeleteClick = { userId ->
            viewUserViewModel.deleteUserById(userId)
            loadUserData()
            Toast.makeText(activity, "User Deleted", Toast.LENGTH_SHORT).show()
        })
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = rvAdapter
    }

    private fun settingUpClickListener() {
        addUserButton.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(AddUserDetailFragment())
        }

        showPostButton.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ShowPostFragment())
        }
    }

    private fun initializeViews(view: View) {
        noUserTv = view.findViewById(R.id.txtNoUserFound)
        addUserButton = view.findViewById(R.id.btnAdd)
        showPostButton = view.findViewById(R.id.btnShowPost)
        rv = view.findViewById(R.id.recyclerView)
    }

    private fun loadUserData() {
        viewUserViewModel.getAllUsers()
    }
}