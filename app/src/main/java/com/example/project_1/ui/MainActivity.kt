package com.example.project_1.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_1.R
import com.example.project_1.data.remote.RetrofitObject
import com.example.project_1.ui.viewUser.ViewUserDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            loadFragment(ViewUserDetailFragment())
        }
        makeApiCall()
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

    private fun makeApiCall() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                RetrofitObject.getRetroFitInstance().getUser()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, " Failed to fetch", Toast.LENGTH_SHORT).show()
                }
                return@launch
            }

            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}