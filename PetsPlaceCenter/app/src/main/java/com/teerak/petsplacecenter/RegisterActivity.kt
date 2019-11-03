package com.teerak.petsplacecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun onClickReset(view: View) {
        name.setText("")
        lname.setText("")
        username.setText("")
        password.setText("")
        tel.setText("")
    }

    fun onClickRegister(view: View) {
        val api : API = Retrofit.Builder()
            .baseUrl("http://172.17.100.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API :: class.java)

        api.addUser(
            username.text.toString(),
            password.text.toString(),
            name.text.toString(),
            lname.text.toString(),
            tel.text.toString()).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful()){

                    Toast.makeText(applicationContext, "Successfully Registered", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailur " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
