package com.teerak.petsplacecenter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PetsPlaceBooking(
    @Expose
    @SerializedName( "booking_id") val booking_id: Int,

    @Expose
    @SerializedName( "pet_name") val pet_name: String,

    @Expose
    @SerializedName( "place_id") val place_id: Int,

    @Expose
    @SerializedName( "check_in") val check_in: String,

    @Expose
    @SerializedName( "check_out") val check_out: String,

    @Expose
    @SerializedName( "user_id") val user_id: Int,

    @Expose
    @SerializedName( "room_id") val room_id: Int
){}