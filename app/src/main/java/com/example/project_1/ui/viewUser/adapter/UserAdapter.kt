package com.example.project_1.ui.viewUser.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.data.local.userDetail.UserDetailData

class UserAdapter(
    private var users: List<UserDetailData>, private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userIdTextView: TextView = view.findViewById(R.id.sampleTxtUserId)
        private val usernameTextView: TextView = view.findViewById(R.id.sampleTxtusername)
        private val phoneTextView: TextView = view.findViewById(R.id.sampleTxtPhone)
        private val deleteButton: Button = view.findViewById(R.id.btnDeleteUser)

        fun setData(user: UserDetailData, onDeleteClick: (String) -> Unit) {
            userIdTextView.text = user.userId
            usernameTextView.text = user.name
            phoneTextView.text = user.phone
            deleteButton.setOnClickListener {
                onDeleteClick(user.userId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_view, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(users[position], onDeleteClick)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun updateData(newUsers: List<UserDetailData>) {
        users = newUsers
        notifyDataSetChanged()
    }
}