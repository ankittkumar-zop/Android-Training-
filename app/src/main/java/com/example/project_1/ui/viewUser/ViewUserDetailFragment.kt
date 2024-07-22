package com.example.project_1.ui.viewUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.R
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.addUser.AddUserDetailFragment
import com.example.project_1.ui.showPost.ShowPostFragment
import com.example.project_1.ui.viewUser.adapter.UserAdapter

class ViewUserDetailFragment : Fragment() {
    private lateinit var noUserTv: TextView
    private lateinit var userDao : UserDetailDao
    private lateinit var viewUserViewModel : ViewUserViewModel
    private val rvAdapter : UserAdapter by lazy {
        UserAdapter(emptyList(), onDeleteClick = { userId ->
            viewUserViewModel.deleteUserById(userId)
            setData()
            Toast.makeText(activity, "User Deleted" , Toast.LENGTH_SHORT).show()
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_user_detail, container, false)
        noUserTv = view.findViewById(R.id.txtNoUserFound)
        val addButtonInFragment1:Button = view.findViewById(R.id.btnAdd)
        val showPostButton:Button = view.findViewById(R.id.btnShowPost)

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }
        val userDatabase = UserDetailDatabase.getDatabase(requireContext())
        userDao = userDatabase.userDetailDao()
        val factory = ViewUserViewModelFactory(userDao)
        viewUserViewModel = ViewModelProvider(this, factory).get(ViewUserViewModel::class.java)
        addButtonInFragment1.setOnClickListener{
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(AddUserDetailFragment())
        }

        showPostButton.setOnClickListener{
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(ShowPostFragment())
        }
        return view
    }
    private fun setData() {
        val users = userDao.getDetails()
        rvAdapter.updateData(users)
        noUserTv.isVisible = users.isEmpty()
    }
    override fun onResume() {
        super.onResume()
        setData()
    }
}