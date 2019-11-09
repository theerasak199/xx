package com.teerak.petsplacecenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.booking_item_layout.view.*

class CheckBookingAdapter(val items : List<PetsPlaceBooking>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.booking_item_layout, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPet?.text = /*"Pet name : " +*/ items[position].pet_name
        holder.tvCheck_in?.text = items[position].check_in
        holder.tvCheck_out?.text = items[position].check_out
        when (items[position].place_id) {
            1 -> holder.tvPlace?.text = "ฟาร์มฮัก"
            2 -> holder.tvPlace?.text = "วนิดาฟาร์ม"
            else -> {
                holder.tvPlace?.text = "ไม่ได้เลือกสถานบริการ"
            }
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvPet : TextView = view.tv_pet
    val tvCheck_in = view.tv_check_in
    val tvCheck_out = view.tv_check_out
    val tvPlace = view.tv_place
}

