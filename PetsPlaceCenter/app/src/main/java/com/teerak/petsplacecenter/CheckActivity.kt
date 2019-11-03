package com.teerak.petsplacecenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_check.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckActivity : AppCompatActivity() {
    var bookingList = arrayListOf<PetsPlaceBooking>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        recycler_view.layoutManager =
            LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()
        callBookingData()
    }

    fun callBookingData() {
        bookingList.clear();

        val serv: API = Retrofit.Builder()
            .baseUrl("http://172.17.100.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)

        serv.retrievePet()
            .enqueue(object : Callback<List<PetsPlaceBooking>> {

                override fun onResponse(
                    call: Call<List<PetsPlaceBooking>>,
                    response: Response<List<PetsPlaceBooking>>
                ) {
                    response.body()?.forEach {
                        bookingList.add(
                            PetsPlaceBooking(
                                it.booking_id,
                                it.pet_name,
                                it.check_in,
                                it.check_out,
                                it.user_id,
                                it.price
                            )
                        )
                    }

                    recycler_view.adapter = CheckBookingAdapter(bookingList, applicationContext)
                }

                override fun onFailure(call: Call<List<PetsPlaceBooking>>, t: Throwable) =
                    t.printStackTrace()

            })
    }

    fun back(v: View){ finish() }
}