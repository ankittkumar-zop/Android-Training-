package com.example.project_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.data.local.UserDetailDao
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.addUser.AddUserDetailFragment
import com.example.project_1.ui.showPost.ShowPostFragment
import com.example.project_1.ui.viewUser.adapter.UserAdapter
import com.example.project_1.ui.viewUser.ViewUserViewModel


class ViewUserDetailFragment : Fragment() {
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUserTv: TextView
    private lateinit var userDao : UserDetailDao
    private lateinit var viewUserViewModel : ViewUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_user_detail, container, false)
        noUserTv = view.findViewById(R.id.txtNoUserFound)
        val addButtonInFragment1:Button = view.findViewById(R.id.btnAdd)
        val showPostButton:Button = view.findViewById(R.id.btnShowPost)
        val rv:RecyclerView = view.findViewById(R.id.recyclerView)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rvAdapter = UserAdapter(emptyList(), onDeleteClick = { userId ->
            viewUserViewModel.deleteUserById(userId)
            setData()
            Toast.makeText(activity, "User Deleted" , Toast.LENGTH_SHORT).show()
        })
        rv.adapter = rvAdapter

        val userDatabase = UserDetailDatabase.getDatabase(requireContext())
        userDao = userDatabase.userDetailDao()
        viewUserViewModel = ViewUserViewModel(userDao)

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