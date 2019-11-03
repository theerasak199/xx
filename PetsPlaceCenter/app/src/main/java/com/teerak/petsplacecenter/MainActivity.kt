package com.teerak.petsplacecenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.profile.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun back(v: View){ setContentView(R.layout.main)}

    fun login(v: View) {
        setContentView(R.layout.main)
    }

    fun register(v: View) {
        val intent = Intent ( this, RegisterActivity::class.java)
        startActivity(intent)
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

    fun onClickBooking(view: View) {
        val intent = Intent ( this, BookingActivity::class.java)
        startActivity(intent)
    }

    fun onClickCheck(view: View) {
        val intent = Intent ( this, CheckActivity::class.java)
        startActivity(intent)
    }
}
