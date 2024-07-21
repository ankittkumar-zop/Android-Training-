package com.example.project_1.ui.viewUser.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.R
import com.example.project_1.data.local.UserDetailData

class UserAdapter(private val onDeleteClick: (String) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private  var users : List<UserDetailData> = emptyList()
    class UserViewHolder (view: View) : RecyclerView.ViewHolder(view.rootView){
        private val sampleUserIdTextView : TextView = view.findViewById(R.id.sampleTxtUserId)
        private val sampleUsernameTextView : TextView = view.findViewById(R.id.sampleTxtusername)
        private val samplePhoneTextView : TextView = view.findViewById(R.id.sampleTxtPhone)
        private val sampleDeleteButton : Button = view.findViewById(R.id.btnDeleteUser)

        fun setData(users: UserDetailData , onDeleteClick: (String) -> Unit) {
            with(users) {
                sampleUserIdTextView.text = userId
                sampleUsernameTextView.text = name
                samplePhoneTextView.text = phone
                sampleDeleteButton.setOnClickListener{
                    onDeleteClick(users.userId)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_view, parent, false)
        return UserViewHolder(view)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<UserDetailData>){
        users = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.setData(users[position] , onDeleteClick)
    }
}