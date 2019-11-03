package com.teerak.petsplacecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_booking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
    }
    fun back(view: View){ finish() }

    fun btnbooking(view: View) {
        val api : API = Retrofit.Builder()
            .baseUrl("http://172.17.100.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API :: class.java)
        var roomBooking : RadioGroup = findViewById(R.id.priceBooking)
        var selectedId:Int = roomBooking.checkedRadioButtonId
        var priceBooking: RadioButton = findViewById(selectedId)
        var priceRoom:Int
        if (priceBooking.text=="VIP"){priceRoom=200}else{priceRoom=150}
        val userID : Int = 1//--------------------------------------------------------------------

        api.insertBooking(
            namePet.text.toString(),
            check_in.text.toString(),
            check_out.text.toString(),
            userID,
            priceRoom).enqueue(object : Callback<PetsPlaceBooking> {

            override fun onResponse(call: Call<PetsPlaceBooking>, response: Response<PetsPlaceBooking>) {
                if (response.isSuccessful()){

                    Toast.makeText(applicationContext, "Successfully Booking", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PetsPlaceBooking>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailur " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
