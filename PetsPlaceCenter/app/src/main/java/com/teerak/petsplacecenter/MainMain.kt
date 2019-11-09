package com.teerak.petsplacecenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main_main.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import kotlinx.android.synthetic.main.profile.view.*

class MainMain : AppCompatActivity() {
    var userProfile = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)

        /*val mUserId = intent.getStringExtra("mUserId")
        val mUsername = intent.getStringExtra("mUsername")
        val mPassword = intent.getStringExtra("mPassword")
        val mName = intent.getStringExtra("mName")
        val mLname = intent.getStringExtra("mLname")
        val mTel = intent.getStringExtra("mTel")*/
    }

    fun logout(view: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.profile, null)

        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.btnCancle.setOnClickListener {
            mAlertDialog.dismiss()
        }
        mDialogView.btnLogout.setOnClickListener {
            setContentView(R.layout.activity_main)
            mAlertDialog.dismiss()
        }
    }

    fun goProfile(v: View) {
        val intent = Intent ( this, ProfileActivity::class.java)
        startActivity(intent)
    }

    fun calBook(view: View) {
        val intent = Intent ( this, ServiceDetailActivity::class.java)
        startActivity(intent)
    }

    fun onClickBooking(view: View) {
        val intent = Intent ( this, BookingActivity::class.java)
        startActivity(intent)
    }

    fun onClickCheck(view: View) {
        val intent = Intent ( this, CheckActivity::class.java)
        startActivity(intent)
    }
}
