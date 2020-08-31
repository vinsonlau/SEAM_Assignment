package com.example.seamassignment.api

import com.example.seamassignment.Model.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

private const val ROUTING_NAME : String = "products"

interface ProductAPI {

    @GET(ROUTING_NAME)
    fun getProduct(): Call<ArrayList<Product>>

    @GET("$ROUTING_NAME/{id}")
    fun getProductByID(@Path("id") productID:String ): Call<ArrayList<Product>>

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST(ROUTING_NAME)
    //The parameter will accept the customer order model class to create json
    //post request in order to create new order
    fun createProduct(
        @Body product: Product
    ):Call<ResponseBody>

}