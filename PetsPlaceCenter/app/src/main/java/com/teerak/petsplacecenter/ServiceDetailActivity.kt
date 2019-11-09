package com.teerak.petsplacecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_service_detail.*

class ServiceDetailActivity : AppCompatActivity() {
    var petType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_detail)

        val petTypeSpinner: Spinner = spinner_petType;
        val petTypeArray = resources.getStringArray(R.array.petType_array)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, petTypeArray)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        petTypeSpinner.adapter = arrayAdapter

        petTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                paren: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                petType = position
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
    fun back(v: View){ finish() }

    fun calculateBooking(v: View){
        var calPrice:Double = 0.0
        val petWeightKg = petWeight.text.toString().toInt()
        when{
            (petWeightKg<4)and(petType==1) -> calPrice=80.0
            (petWeightKg<4)and(petType==2) -> calPrice=100.0

            (petWeightKg<=8)and(petType==1) -> calPrice=100.0
            (petWeightKg<=8)and(petType==2) -> calPrice=120.0

            (petWeightKg<=12)and(petType==1) -> calPrice=120.0
            (petWeightKg<=12)and(petType==2) -> calPrice=150.0

            (petWeightKg<=16)and(petType==1) -> calPrice=150.0
            (petWeightKg<=16)and(petType==2) -> calPrice=180.0

            (petWeightKg<=20)and(petType==1) -> calPrice=180.0
            (petWeightKg<=20)and(petType==2) -> calPrice=200.0

            (petWeightKg<=25)and(petType==1) -> calPrice=200.0
            (petWeightKg<=25)and(petType==2) -> calPrice=220.0

            (petWeightKg<=30)and(petType==1) -> calPrice=220.0
            (petWeightKg<=30)and(petType==2) -> calPrice=250.0

            (petWeightKg<=35)and(petType==1) -> calPrice=250.0
            (petWeightKg<=35)and(petType==2) -> calPrice=300.0

            (petWeightKg<=40)and(petType==1) -> calPrice=300.0
            (petWeightKg<=40)and(petType==2) -> calPrice=350.0

            (petWeightKg<=45)and(petType==1) -> calPrice=350.0
            (petWeightKg<=45)and(petType==2) -> calPrice=400.0

            (petWeightKg<=50)and(petType==1) -> calPrice=400.0
            (petWeightKg<=50)and(petType==2) -> calPrice=450.0

            (petWeightKg<=55)and(petType==1) -> calPrice=450.0
            (petWeightKg<=55)and(petType==2) -> calPrice=500.0

            (petWeightKg<=60)and(petType==1) -> calPrice=500.0
            (petWeightKg<=60)and(petType==2) -> calPrice=550.0

            (petWeightKg>60)and(petType==1) -> calPrice=550.0
            (petWeightKg>60)and(petType==2) -> calPrice=600.0
        }
        var roomBooking : RadioGroup = findViewById(R.id.roomBook)
        var selectedId:Int = roomBooking.checkedRadioButtonId
        var roomSelect: RadioButton = findViewById(selectedId)
        if (roomSelect.text=="VIP"){calPrice+=50}
        calPrice*=dayDeposit.text.toString().toInt()
        if (dayDeposit.text.toString().toInt()>6){calPrice=calPrice-(0.1*calPrice)}
        calculateText.setText("ค่าบริการ "+calPrice+" บาท")
    }
}
