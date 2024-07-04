package com.example.project_1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

data class User(val userId: String, val name: String, val phone: String)

class MainActivity : AppCompatActivity() {

    private val userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //for edit text box
        val userIdEditText: EditText = findViewById(R.id.userId)
        val nameEditText: EditText = findViewById(R.id.nameId)
        val phoneEditText: EditText = findViewById(R.id.phoneId)
        val enterIdToViewEditText: EditText = findViewById(R.id.viewUserId)

        //for button
        val addUserButton: Button = findViewById(R.id.submit_button)
        val viewUserButton: Button = findViewById(R.id.viewUserButton)

        addUserButton.setOnClickListener {
            val userId = userIdEditText.text.toString()
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (userId.isNotBlank() && name.isNotBlank() && phone.isNotBlank()) {
                val user = User(userId, name, phone)
                userList.add(user)

                userIdEditText.text.clear()
                nameEditText.text.clear()
                phoneEditText.text.clear()
            } else {
                Toast.makeText(this, " All fields required", Toast.LENGTH_SHORT).show()
            }

        }
        viewUserButton.setOnClickListener {

            val viewId = enterIdToViewEditText.text.toString()
            val user = userList.find { it.userId == viewId }
            if (user != null) {
                val intent = Intent(this, UserDetail::class.java).apply {
                    putExtra(USER_ID, user.userId)
                    putExtra(USERNAME, user.name)
                    putExtra(USER_PHONE, user.phone)

                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Missing User ", Toast.LENGTH_SHORT).show()
            }
        }
    }
    companion object {
        const val USER_ID = "user_adhar"
        const val USERNAME = "username"
        const val USER_PHONE = "user_phone"
    }
}