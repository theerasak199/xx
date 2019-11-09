package com.teerak.petsplacecenter

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_booking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditBookingActivity : AppCompatActivity() {
    val createClient = API.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_booking)

        val mBookId = intent.getStringExtra("mBookId")
        val mPetName = intent.getStringExtra("mPetName")
        val mCheckIn = intent.getStringExtra("mCheckIn")
        val mCheckOut = intent.getStringExtra("mCheckOut")
        val mPlaceId = intent.getStringExtra("mPlaceId")
        val mRoomId = intent.getStringExtra("mRoomId")


        editBookID.setVisibility(View.GONE)

        editBookID.setText(mBookId)
        editNamePet.setText(mPetName)
        edit_check_in.setText(mCheckIn)
        edit_check_out.setText(mCheckOut)
        when (mPlaceId) {
            "1" -> editPlace.setText("ฟาร์มฮัก")
            "2" -> editPlace.setText("วนิดาฟาร์ม")
            else -> {
                editPlace.setText("ไม่ได้เลือกสถานบริการ")
            }
        }
        when (mRoomId) {
            "1" -> editRoom.setText("ห้องธรรมดา")
            "2" -> editRoom.setText("ห้อง VIP")
            else -> {
                editRoom.setText("ไม่ได้เลือกห้อง")
            }
        }
    }

    fun back(view: View){ finish() }

    fun saveBooking(v: View){
        createClient.updateBooking(
            editBookID.text.toString().toInt(),
            editNamePet.text.toString()).enqueue(object : Callback<PetsPlaceBooking> {

            override fun onResponse(call: Call<PetsPlaceBooking>, response: Response<PetsPlaceBooking>){
                if(response.isSuccessful){
                    Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<PetsPlaceBooking>, t:Throwable){
                Toast.makeText(applicationContext,"Error onFailure "+t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun deleteBooking(v:View){
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            createClient.deleteBooking(editBookID.text.toString().toInt())
                .enqueue(object : Callback<PetsPlaceBooking> {
                    override fun onResponse(call: Call<PetsPlaceBooking>, response: Response<PetsPlaceBooking>) {
                        if (response.isSuccessful()) {
                            Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<PetsPlaceBooking>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            finish()
        }
        val negativeButtonClick = { dialog: DialogInterface, which :Int-> dialog.cancel() }
        builder.setTitle("Warning")
        builder.setMessage("Do you want to delete the booking?")
        builder.setNegativeButton("No", negativeButtonClick)
        builder.setPositiveButton("Yes", positiveButtonClick)
        builder.show() }
}
