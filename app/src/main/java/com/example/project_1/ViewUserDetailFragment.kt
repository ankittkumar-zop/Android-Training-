package com.example.project_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView


class ViewUserDetailFragment : Fragment() {
    private lateinit var rvAdapter: UserAdapter
    private lateinit var noUserTv: TextView

    private lateinit var userDatabase : UserDetailDatabase
    private lateinit var userDao : UserDetailDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_user_detail, container, false)
        noUserTv = view.findViewById(R.id.txtNoUserFound)
        val addButtonInFragment1:Button = view.findViewById(R.id.btnAdd)
        val rv:RecyclerView = view.findViewById(R.id.recyclerView)
        rvAdapter = UserAdapter()
        rv.adapter = rvAdapter
        userDatabase = UserDetailDatabase.getDatabase((activity as MainActivity).applicationContext)
        userDao = userDatabase.userDetailDao()

        addButtonInFragment1.setOnClickListener{
            val mainActivity = activity as? MainActivity
            mainActivity?.loadFragment(AddUserDetailFragment())
        }
        return view
    }
    private fun setData() {
        val mainActivity = activity as? MainActivity

        val users = userDao.getDetails()
        rvAdapter.updateData(users)
        noUserTv.isVisible = users.isEmpty() ?: true
    }

    override fun onResume() {
        super.onResume()
        setData()
    }
}