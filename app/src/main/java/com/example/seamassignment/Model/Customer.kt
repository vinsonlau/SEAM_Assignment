package com.example.seamassignment.Model

data class Customer(

    val customerID: String,
    val name: String?,
    val email: String?,
    val address: String?,
    val phoneNo: String?,
    val faxNo: String?

) {
    override fun toString(): String {
//        return "Customers return successful \n" +
//                "(customerID='$customerID', \n" +
//                "name='$name' , \n" +
//                "email='$email' , \n" +
//                "address='$address' , \n" +
//                "phoneNo='$phoneNo' , \n" +
//                "faxNo='$faxNo')"
        return "$name"
    }
}