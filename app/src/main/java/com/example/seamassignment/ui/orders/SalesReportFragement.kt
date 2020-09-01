package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seamassignment.R

class SalesReportFragement : Fragment() {

    companion object{
        fun newInstance() = SalesReportFragement()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sales_report_fragment, container, false)
    }
}