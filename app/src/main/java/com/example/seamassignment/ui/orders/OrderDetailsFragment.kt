package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seamassignment.R
import kotlinx.android.synthetic.main.order_details_fragment.*


class OrderDetailsFragment : Fragment() {

    companion object{
        fun newInstance() = OrderDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillTable()
    }

    private fun fillTable(){
        product_1.text = "Nike Socks SSW-132"
        quantity_1.text = "1000"
        unitPrice_1.text = "RM 49.90"
        discount_1.text = "-"
        totalPrice_1.text = "RM 49900.00"

        product_2.text = "Nike Bag BGE-007"
        quantity_2.text = "500"
        unitPrice_2.text = "RM 99.90"
        discount_2.text = "-"
        totalPrice_2.text = "RM 49950.00"

        product_3.text = "Nike Top TSA-130"
        quantity_3.text = "200"
        unitPrice_3.text = "RM 124.90"
        discount_3.text = "-"
        totalPrice_3.text = "RM 24980.00"
    }
}