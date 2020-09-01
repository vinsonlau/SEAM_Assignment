package com.example.seamassignment.api

import com.example.seamassignment.Model.Staff
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

private const val ROUTING_NAME : String = "staffs"

interface StaffAPI {

    @GET(ROUTING_NAME)
    fun getStaffList(): Call<ArrayList<Staff>>

    @GET("$ROUTING_NAME/{id}")
    fun getStaffByID(@Path("id") staffID:String ): Call<Staff>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(ROUTING_NAME)
    //The parameter will accept the customer order model class to create json
    //post request in order to create new order
    fun createCustomer(
        @Body staff: Staff
    ): Call<ResponseBody>

}