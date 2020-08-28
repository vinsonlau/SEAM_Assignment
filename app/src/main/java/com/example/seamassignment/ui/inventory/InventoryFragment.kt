package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.seamassignment.Model.CustomerOrders
import com.example.seamassignment.R
import com.example.seamassignment.RetrofitClient
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class InventoryFragment : Fragment() {

    private lateinit var notificationsViewModel: InventoryViewModel
    private val list = ArrayList<CustomerOrders>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.inventory_page, container, false)
        getCustomerOrderByID("OD101",root)

        return root
    }

    private fun getCustomerOrderByID(customerID: String, root: View){
        /*rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)*/

        RetrofitClient.customerOrderInstance.getCustomerOrdersByID(customerID).enqueue(object: Callback<ArrayList<CustomerOrders>>{
            override fun onFailure(call: Call<ArrayList<CustomerOrders>>, t: Throwable) {
                TODO("Not yet implemented")
            }
            override fun onResponse(
                call: Call<ArrayList<CustomerOrders>>,
                response: Response<ArrayList<CustomerOrders>>
            ) {
                val customerOrder: ArrayList<CustomerOrders>? = response.body()

                val category_1 = root.findViewById<TextView>(R.id.tvCategory1)
                category_1.setText(customerOrder.toString())
            }
        })
    }
}