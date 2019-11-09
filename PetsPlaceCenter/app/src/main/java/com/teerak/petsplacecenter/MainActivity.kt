package com.teerak.petsplacecenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    //var dbHandler: DatabaseHelper? = null
    var userProfile = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun login(view: View) {
        val api : API = Retrofit.Builder()
            .baseUrl("http://172.17.100.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API :: class.java)

        api.loginUser(
            username.text.toString(),
            password.text.toString()).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful()){
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()

                    /*response.body()?.let {
                        userProfile.add(
                            User(
                                it.user_id,
                                it.username,
                                it.password,
                                it.user_Name,
                                it.user_Lname,
                                it.user_tel
                            )
                        )
                    }
                    //dbHandler?.insertProfile(User(user_id = userProfile[0].user_id, username = userProfile[0].username, password = userProfile[0].password, user_Name = userProfile[0].user_Name, user_Lname = userProfile[0].user_Lname, user_tel = userProfile[0].user_tel))
                    val profile = userProfile[0]
                    val intent = Intent(applicationContext, MainMain::class.java)
                    intent.putExtra("mUserId",profile.user_id.toString())
                    intent.putExtra("mUsername",profile.username)
                    intent.putExtra("mPassword" ,profile.password)
                    intent.putExtra("mName" ,profile.user_Name)
                    intent.putExtra("mLname",profile.user_Lname)
                    intent.putExtra("mTel",profile.user_tel)*/
                    logon()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailur " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun logon() {
        startActivity(Intent ( this, MainMain::class.java))
    }

    fun register(v: View) {
        val intent = Intent ( this, RegisterActivity::class.java)
        startActivity(intent)
    }
}