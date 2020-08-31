package com.example.seamassignment.Model

data class Product(
    val productID: String,
    val model: String?,
    val manufacturer: String?,
    val category: String?,
    val description: String?,
    val unitPrice: Double?,
    val status: String?,
    val quantity: Int?,
    val remark: String?,
    val targetStockLevel: Int?,
    val reorderStockLevel: Int?,
    val location: String?,
    val staffID: String?

) {
    override fun toString(): String {
        return "Product return successful \n" +
                "(Product ID='$productID', \n" +
                "Model=$model\n" +
                "Manufacturer=$manufacturer\n" +
                "Category=$category\n" +
                "Description=$description\n" +
                "Unit Price=$unitPrice\n" +
                "Status=$status\n" +
                "Quantity=$quantity\n" +
                "Remark=$remark\n" +
                "Target Stock Level=$targetStockLevel\n" +
                "Reorder Stock Level=$reorderStockLevel\n" +
                "Location=$location\n" +
                "Staff ID=$staffID)"
    }
}