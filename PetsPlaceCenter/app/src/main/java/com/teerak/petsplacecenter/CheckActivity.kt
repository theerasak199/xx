package com.teerak.petsplacecenter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
            LinearLayoutManager(applicationContext)
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int , view: View){
                Toast.makeText(applicationContext,"You click on : "+bookingList[position].pet_name,
                    Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, EditBookingActivity::class.java)
                intent.putExtra("mBookId",bookingList[position].booking_id.toString())
                intent.putExtra("mPetName",bookingList[position].pet_name)
                intent.putExtra("mCheckIn" ,bookingList[position].check_in)
                intent.putExtra("mCheckOut" ,bookingList[position].check_out)
                intent.putExtra("mPlaceId",bookingList[position].place_id.toString())
                intent.putExtra("mRoomId",bookingList[position].room_id.toString())
                startActivity(intent)
            }
        })
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
                                it.place_id,
                                it.check_in,
                                it.check_out,
                                it.user_id,
                                it.room_id
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

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }
    fun RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {

            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }
}