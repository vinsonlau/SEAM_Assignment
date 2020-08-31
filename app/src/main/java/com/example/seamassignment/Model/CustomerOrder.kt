package com.example.seamassignment.Model

data class CustomerOrders(
    val customerOrderID: String,
    val customerID: String?,
    val staffID: String?,
    val date: String?,
    val productID: String?,
    val productQuantity: Int?,
    val discount: Double?


) {
    override fun toString(): String {
        return "CustomerOrders return successful \n" +
                "(customerOrderID='$customerOrderID', \n" +
                "customerID=$customerID\n" +
                ", staffID=$staffID\n" +
                ", date=$date\n" +
                ", productID=$productID, productQuantity=$productQuantity, discount=$discount)"
    }
}