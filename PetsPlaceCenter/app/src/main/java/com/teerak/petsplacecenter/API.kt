package com.teerak.petsplacecenter

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @GET( "allpetsbooking")
    fun retrievePet(): Call<List<PetsPlaceBooking>>

    @FormUrlEncoded
    @POST( "booking")
    fun insertBooking(
        @Field("pet_name") pet_name: String,
        @Field("check_in") check_in: String,
        @Field("check_out") check_out: String,
        @Field("user_id") user_id: Int,
        @Field("price") price: Int): Call<PetsPlaceBooking>

    @FormUrlEncoded
    @POST( "register")
    fun addUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("user_Name") user_Name: String,
        @Field("user_Lname") user_Lname: String,
        @Field("user_tel") user_tel: String): Call<User>
}