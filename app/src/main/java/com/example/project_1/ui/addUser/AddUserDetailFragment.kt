package com.example.project_1.ui.addUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.project_1.R
import com.example.project_1.ui.MainActivity
import com.example.project_1.ui.viewUser.ViewUserDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserDetailFragment : Fragment() {

    private val addUserViewModel: AddUserViewModel by viewModels()
    private lateinit var userIdEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addUserButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializer(view)
        handleUser(view)
    }

    private fun initializer(view: View) {
        userIdEditText = view.findViewById(R.id.edtUserId)
        usernameEditText = view.findViewById(R.id.edtUsername)
        phoneEditText = view.findViewById(R.id.edtPhone)
    }

    private fun handleUser(view: View) {
        addUserButton = view.findViewById(R.id.btnAddUser)
        addUserButton.setOnClickListener {
            val userId = userIdEditText.text.toString()
            val username = usernameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            addUserViewModel.validateUser(userId, username, phone) { isSuccess ->
                if (isSuccess) {
                    clearText()
                    Toast.makeText(context, "User Added", Toast.LENGTH_SHORT).show()
                    (activity as? MainActivity)?.loadFragment(ViewUserDetailFragment())
                } else {
                    Toast.makeText(context, "All fields required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearText() {
        userIdEditText.text.clear()
        usernameEditText.text.clear()
        phoneEditText.text.clear()
    }
}