package com.example.seamassignment.Model

data class Staff(

    val staffID: String,
    val staffRole: String?,
    val name: String?,
    val email: String?

) {
    override fun toString(): String {
//        return "Staffs return successful \n" +
//                "(staffID='$staffID', \n" +
//                "staffRole='$staffRole' , \n" +
//                "name='$name' , \n" +
//                "email='$email')"
        return "$name"
    }
}