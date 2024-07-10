package com.example.project_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class AddUserDetailFragment : Fragment() {

    private var userDatabase : UserDetailDatabase? = null
    private var userDao : UserDetailDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_user_detail, container, false)

        val userIdEditText:EditText = view.findViewById(R.id.edtUserId)
        val usernameEditText:EditText = view.findViewById(R.id.edtUsername)
        val phoneEditText:EditText = view.findViewById(R.id.edtPhone)
        val addUserbutton: Button = view.findViewById(R.id.btnAddUser)

        userDatabase = UserDetailDatabase.getDatabase((activity as MainActivity).applicationContext)
        userDao = userDatabase?.userDetailDao()

        addUserbutton.setOnClickListener{
            val userId = userIdEditText.text.toString()
            val username = usernameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if(userId.isNotBlank() && username.isNotBlank() && phone.isNotBlank()){
                val user = UserDetailData( userId, username, phone)
                userDao?.insertUserDetail(user)
                userIdEditText.text.clear()
                usernameEditText.text.clear()
                phoneEditText.text.clear()
                Toast.makeText(activity, "User Added " ,Toast.LENGTH_SHORT).show()

                val mainActivity = activity as? MainActivity
                mainActivity?.loadFragment(ViewUserDetailFragment())
            }
            else{
                Toast.makeText(activity, "All fields required " ,Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }


}