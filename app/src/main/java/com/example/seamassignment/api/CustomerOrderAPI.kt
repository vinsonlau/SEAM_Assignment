package com.example.seamassignment.api

import com.example.seamassignment.Model.CustomerOrders
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

private const val ROUTING_NAME : String = "customerOrders"

interface CustomerOrderAPI {

    @GET(ROUTING_NAME)
    fun getCustomerOrders(): Call<ArrayList<CustomerOrders>>

    @GET("$ROUTING_NAME/{id}")
    fun getCustomerOrdersByID(@Path("id") customerID:String ): Call<ArrayList<CustomerOrders>>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(ROUTING_NAME)
    //The parameter will accept the customer order model class to create json
    //post request in order to create new order
    fun createCustomerOrder(
        @Body customerOrders: CustomerOrders
    ):Call<ResponseBody>

}