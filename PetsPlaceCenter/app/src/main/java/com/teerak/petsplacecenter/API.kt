package com.teerak.petsplacecenter

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface API {
    @GET( "allpetsbooking")
    fun retrievePet(): Call<List<PetsPlaceBooking>>

    @FormUrlEncoded
    @POST( "booking")
    fun insertBooking(
        @Field("pet_name") pet_name: String,
        @Field("place_id") place_id: Int,
        @Field("check_in") check_in: String,
        @Field("check_out") check_out: String,
        @Field("user_id") user_id: Int,
        @Field("room_id") room_id: Int): Call<PetsPlaceBooking>

    @FormUrlEncoded
    @POST( "register")
    fun addUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("user_Name") user_Name: String,
        @Field("user_Lname") user_Lname: String,
        @Field("user_tel") user_tel: String): Call<User>

    @FormUrlEncoded
    @POST( "login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String): Call<User>



    @FormUrlEncoded
    @PUT("petBooking_id/{booking_id}")
    fun updateBooking(
        @Path("booking_id") booking_id: Int,
        @Field("pet_name")pet_name: String): Call<PetsPlaceBooking>

    @DELETE("petBooking_id/{booking_id}")
    fun deleteBooking(
        @Path("booking_id")booking_id: Int): Call<PetsPlaceBooking>

    companion object {
        fun create(): API {
            val petClient: API = Retrofit.Builder()
                .baseUrl("http://172.17.100.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
            return petClient
        }
    }
}