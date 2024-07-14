package com.example.project_1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_1.data.local.UserDetailData


class UserAdapter(private  var users : List<UserDetailData>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

//    private  var users = listOf<UserDetailData>()
    class UserViewHolder (view: View) : RecyclerView.ViewHolder(view.rootView){
        private val sampleUserIdTextView : TextView = view.findViewById(R.id.sampleTxtUserId)
        private val sampleUsernameTextView : TextView = view.findViewById(R.id.sampleTxtusername)
        private val samplePhoneTextView : TextView = view.findViewById(R.id.sampleTxtPhone)
        private val sampleDeleteIcon : ImageView = view.findViewById(R.id.deleteIcon)

        fun setData(users: UserDetailData) {
            with(users) {
                sampleUserIdTextView.text = userId
                sampleUsernameTextView.text = name
                samplePhoneTextView.text = phone
            }
        }

        fun delete(user : UserDetailData, onDeleteClick:(UserDetailData) -> Unit ){
            sampleUserIdTextView.text = user.userId
            sampleUsernameTextView.text = user.name
            samplePhoneTextView.text = user.phone

            sampleDeleteIcon.setOnClickListener{ onDeleteClick.invoke(user)}
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
        holder.setData(users[position])

    }

}