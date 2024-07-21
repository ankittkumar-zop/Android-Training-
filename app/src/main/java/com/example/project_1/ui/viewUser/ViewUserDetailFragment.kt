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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.addUser.AddUserDetailFragment
import com.example.project_1.ui.showPost.ShowPostFragment
import com.example.project_1.ui.viewUser.adapter.UserAdapter


class ViewUserDetailFragment : Fragment() {
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUserTv: TextView
    private lateinit var viewUserViewModel: ViewUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_user_detail, container, false)
        noUserTv = view.findViewById(R.id.txtNoUserFound)
        val addButtonInFragment1: Button = view.findViewById(R.id.btnAdd)
        val showPostButton: Button = view.findViewById(R.id.btnShowPost)
        val rv: RecyclerView = view.findViewById(R.id.recyclerView)

        rv.layoutManager = LinearLayoutManager(requireContext())

        rvAdapter = UserAdapter(emptyList(), onDeleteClick = { userId ->
            viewUserViewModel.deleteUserById(userId)
            loadUserData()
            Toast.makeText(activity, "User Deleted", Toast.LENGTH_SHORT).show()
        })
        rv.adapter = rvAdapter

        val userDatabase = UserDetailDatabase.getDatabase(requireContext())
        val userDao = userDatabase.userDetailDao()
        viewUserViewModel = ViewUserViewModel(userDao)

        addButtonInFragment1.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(AddUserDetailFragment())
        }

        showPostButton.setOnClickListener {
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ShowPostFragment())
        }

        loadUserData()
        return view
    }

    private fun loadUserData() {
        viewUserViewModel.getAllUsers { users ->
            rvAdapter.updateData(users)
            noUserTv.isVisible = users.isEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }
}