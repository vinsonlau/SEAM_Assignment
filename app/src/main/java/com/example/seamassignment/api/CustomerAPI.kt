package com.example.seamassignment.api

import com.example.seamassignment.Model.Customer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

private const val ROUTING_NAME : String = "customers"

interface CustomerAPI {

    @GET(ROUTING_NAME)
    fun getCustomerList(): Call<ArrayList<Customer>>

    @GET("$ROUTING_NAME/{id}")
    fun getCustomerByID(@Path("id") customerID:String ): Call<Customer>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(ROUTING_NAME)
    //The parameter will accept the customer order model class to create json
    //post request in order to create new order
    fun createCustomer(
        @Body customer: Customer
    ): Call<ResponseBody>

}