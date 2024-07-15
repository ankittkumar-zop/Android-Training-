package com.example.project_1.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_1.R
import com.example.project_1.ViewUserDetailFragment
import com.example.project_1.data.remote.ApiDataClass
import com.example.project_1.data.remote.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null ){
            loadFragment(ViewUserDetailFragment())
        }
        makeApiCall()
    }

     fun loadFragment( fragment: Fragment){
       supportFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .addToBackStack(null)
        .commit()
    }
    private fun makeApiCall(){
        val obj = RetrofitObject().getRetroFitInstance()
        obj.getUsers().enqueue(object : Callback<List<ApiDataClass>> {
            override fun onResponse(
                call: Call<List<ApiDataClass>>,
                response: Response<List<ApiDataClass>>
            ){
                if(response.isSuccessful){
                    response.body()
                    Toast.makeText(this@MainActivity, "Success",Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<ApiDataClass>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "failure",Toast.LENGTH_LONG).show()
            }
        }
        )
    }
}