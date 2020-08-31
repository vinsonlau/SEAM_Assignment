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

        initButton()
    }

    private fun initButton(){
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

}