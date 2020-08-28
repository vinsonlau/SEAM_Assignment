package com.example.seamassignment.Model

data class CustomerOrders(
    val customerOrderID: String,
    val customerID: String?,
    val staffID: String?,
    val date: String?,
    val productID: String?,
    val productQuantity: Int?,
    val discount: Double?

)