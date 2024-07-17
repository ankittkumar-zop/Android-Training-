package com.example.project_1.ui.addUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.project_1.ui.MainActivity
import com.example.project_1.R
import com.example.project_1.data.local.userDetail.UserDetailDao
import com.example.project_1.data.local.UserDetailDatabase
import com.example.project_1.ViewUserDetailFragment


class AddUserDetailFragment : Fragment() {

    private var userDatabase : UserDetailDatabase? = null
    private var userDao : UserDetailDao? = null
    private lateinit var addUserViewModel: AddUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_user_detail, container, false)

        val userIdEditText: EditText = view.findViewById(R.id.edtUserId)
        val usernameEditText: EditText = view.findViewById(R.id.edtUsername)
        val phoneEditText: EditText = view.findViewById(R.id.edtPhone)
        val addUserbutton: Button = view.findViewById(R.id.btnAddUser)

        userDatabase = UserDetailDatabase.getDatabase((activity as MainActivity).applicationContext)
        userDao = userDatabase?.userDetailDao()

        addUserViewModel = AddUserViewModel(requireActivity().application)

        addUserbutton.setOnClickListener{
            val userId = userIdEditText.text.toString()
            val username = usernameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val isSuccess = addUserViewModel.validateUser(userId, username, phone)

            if(isSuccess){
                userIdEditText.text.clear()
                usernameEditText.text.clear()
                phoneEditText.text.clear()
                Toast.makeText(context, "User Added ", Toast.LENGTH_SHORT).show()
                val mainActivity= activity as? MainActivity
                mainActivity?.loadFragment(ViewUserDetailFragment())
            }
            else{
                Toast.makeText(context, "All fields required", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}