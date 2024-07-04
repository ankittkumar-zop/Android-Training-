package com.example.project_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class UserDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_detail)

        val userIdTextView: TextView = findViewById(R.id.screen2_userId)
        val nameTextView: TextView = findViewById(R.id.screen2_nameId)
        val phoneTextView: TextView = findViewById(R.id.screen2_phoneId)
        val goToScreen1Button: Button = findViewById(R.id.screen2_closeButton)
        val userId = intent.getStringExtra(MainActivity.USER_ID)
        val name = intent.getStringExtra(MainActivity.USERNAME)
        val phone = intent.getStringExtra(MainActivity.USER_PHONE)

        userIdTextView.text = "User Id: $userId"
        nameTextView.text = "Name: $name"
        phoneTextView.text = "Phone: $phone"

        goToScreen1Button.setOnClickListener {
            finish()
        }

    }
}