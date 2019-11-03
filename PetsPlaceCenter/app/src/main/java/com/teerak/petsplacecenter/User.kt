package com.teerak.petsplacecenter

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
    @Expose
    @SerializedName( "user_id") val user_id: Int,

    @Expose
    @SerializedName( "username") val username: String,

    @Expose
    @SerializedName( "password") val password: String,

    @Expose
    @SerializedName( "user_Name") val user_Name: String,

    @Expose
    @SerializedName( "user_Lname") val user_Lname: String,

    @Expose
    @SerializedName( "user_tel") val user_tel: String
    ){}