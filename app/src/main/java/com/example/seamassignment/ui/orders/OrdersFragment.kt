package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.seamassignment.R
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : Fragment() {

    private lateinit var dashboardViewModel: OrdersViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(OrdersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
        fillTable()
    }

    private fun initButtons(){
        buttonCreateNewOrder.setOnClickListener {
            it?.findNavController()?.navigate(R.id.action_navigation_orders_to_newOrderFragment)
        }

        buttonOrderHistory.setOnClickListener {
            it?.findNavController()?.navigate(R.id.action_navigation_orders_to_orderHistoryFragment)
        }

        buttonSalesReport.setOnClickListener {
            it?.findNavController()?.navigate(R.id.action_navigation_orders_to_salesReportFragement)
        }
    }

    private fun fillTable(){
        orderID_1.text = "O0013"
        customer_1.text = "Lima Bintang"
        totalAmount_1.text = "RM 3000"
        orderDate_1.text = "31/8/2020"
        status_1.text = "Processing"

        orderID_2.text = "O0012"
        customer_2.text = "Lima Bintang"
        totalAmount_2.text = "RM 8000"
        orderDate_2.text = "31/8/2020"
        status_2.text = "Processing"

        orderID_3.text = "O0011"
        customer_3.text = "Flex Sports"
        totalAmount_3.text = "RM 6000"
        orderDate_3.text = "31/8/2020"
        status_3.text = "Processing"

        orderID_4.text = "O0010"
        customer_4.text = "Flex Sports"
        totalAmount_4.text = "RM 2000"
        orderDate_4.text = "29/8/2020"
        status_4.text = "Processing"

        orderID_5.text = "O0009"
        customer_5.text = "Lima Bintang"
        totalAmount_5.text = "RM 17000"
        orderDate_5.text = "28/8/2020"
        status_5.text = "Processing"

        orderID_6.text = "O0008"
        customer_6.text = "Cincai Reseller"
        totalAmount_6.text = "RM 8000"
        orderDate_6.text = "28/8/2020"
        status_6.text = "Packing"

        orderID_7.text = "O0007"
        customer_7.text = "Lima Bintang"
        totalAmount_7.text = "RM 4000"
        orderDate_7.text = "26/8/2020"
        status_7.text = "Packing"

        orderID_8.text = "O0006"
        customer_8.text = "Cincai Reseller"
        totalAmount_8.text = "RM 9000"
        orderDate_8.text = "26/8/2020"
        status_8.text = "Packing"

        orderID_9.text = "O0005"
        customer_9.text = "Cincai Reseller"
        totalAmount_9.text = "RM 14000"
        orderDate_9.text = "26/8/2020"
        status_9.text = "Packing"

        orderID_10.text = "O0004"
        customer_10.text = "Flex Sports"
        totalAmount_10.text = "RM 6000"
        orderDate_10.text = "23/8/2020"
        status_10.text = "Packing"

        orderID_11.text = "O0003"
        customer_11.text = "Lima Bintang"
        totalAmount_11.text = "RM 4000"
        orderDate_11.text = "21/8/2020"
        status_11.text = "Shipping"

        orderID_12.text = "O0002"
        customer_12.text = "Lima Bintang"
        totalAmount_12.text = "RM 5000"
        orderDate_12.text = "19/8/2020"
        status_12.text = "Shipping"

        orderID_13.text = "O0001"
        customer_13.text = "Flex Sports"
        totalAmount_13.text = "RM 2000"
        orderDate_13.text = "17/8/2020"
        status_13.text = "Shipping"
    }

}