package com.teerak.petsplacecenter

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_booking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class BookingActivity : AppCompatActivity() {
    var servicePlace: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        check_in.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    check_in.setText(""+mDay+"-"+mMonth+"-"+mYear)
                },year, month, day)
            dpd.show()
        }
        check_out.setOnClickListener{
            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                    check_out.setText(""+mDay+"-"+mMonth+"-"+mYear)
                },year, month, day)
            dpd.show()
        }

        val placeSpinner: Spinner = spinner_place;
        val placeArray = resources.getStringArray(R.array.placeName_array)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, placeArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        placeSpinner.adapter = arrayAdapter

        placeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(paren: AdapterView<*>, view: View, position: Int, id: Long){
                servicePlace = position
            }

            override fun  onNothingSelected(parent: AdapterView<*>){

            }
        }
    }
    fun back(view: View){ finish() }

    fun btnbooking(view: View) {
        val api : API = Retrofit.Builder()
            .baseUrl("http://172.17.100.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API :: class.java)
        var roomBooking : RadioGroup = findViewById(R.id.roomBook)
        var selectedId:Int = roomBooking.checkedRadioButtonId
        var roomSelect: RadioButton = findViewById(selectedId)
        var room:Int
        if (roomSelect.text=="VIP"){room=2}else{room=1}
        val userID : Int = 1//--------------------------------------------------------------------

        api.insertBooking(
            namePet.text.toString(),
            servicePlace,
            check_in.text.toString(),
            check_out.text.toString(),
            userID,
            room).enqueue(object : Callback<PetsPlaceBooking> {

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
