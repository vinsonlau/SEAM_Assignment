package com.example.seamassignment.api

import com.example.seamassignment.Model.CustomerOrders
import retrofit2.Call
import retrofit2.http.*

private const val ROUTING_NAME : String = "customerOrders"

interface CustomerOrderAPI {

    @GET(ROUTING_NAME)
    fun getCustomerOrders(): Call<ArrayList<CustomerOrders>>

    @GET("$ROUTING_NAME/{id}")
    fun getCustomerOrdersByID(@Path("id") customerID:String ): Call<ArrayList<CustomerOrders>>

    //UNDERGO TESTING/BUG FIXING, NOT YET FINISH
    @FormUrlEncoded
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(ROUTING_NAME)
    fun createCustomerOrder(
        //FIELD IN THE BODY TO CREATE A CUSTOMER ORDER
        @Field("customerOrderID") customerOrderID: String,
        @Field("customerID") customerID:String,
        @Field("staffID") staffID:String,
        @Field("date") date: String,
        @Field("productID")productID: String,
        @Field("productQuantity") productQuantity:Int,
        @Field("discount")discount:Double
    ):Call<CustomerOrders>

}