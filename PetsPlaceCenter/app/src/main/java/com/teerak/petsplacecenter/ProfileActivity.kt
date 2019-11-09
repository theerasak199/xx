package com.teerak.petsplacecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.password
import kotlinx.android.synthetic.main.activity_profile.username

class ProfileActivity : AppCompatActivity() {
    var dbHandler: DatabaseHelper? = null
    var userProfile = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHandler = DatabaseHelper(this)
        dbHandler?.getWritableDatabase()
        userProfile.clear()
        userProfile.addAll(dbHandler!!.getProfile())

        name.isEnabled = false
        lname.isEnabled = false
        username.isEnabled = false
        password.isEnabled = false
        tel.isEnabled = false

        name.setText(userProfile.toString())//-----------------------------
        lname.setText(arrayListOf<User>().toString())
        username.setText(arrayListOf<User>().toString())
        password.setText(arrayListOf<User>().toString())
        tel.setText(dbHandler!!.getProfile().toString())
    }

    fun back(v: View){ finish() }
}
