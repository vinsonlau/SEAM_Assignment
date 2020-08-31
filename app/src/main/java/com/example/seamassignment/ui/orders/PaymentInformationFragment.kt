package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seamassignment.R

class PaymentInformationFragment : Fragment() {

    companion object{
        fun newInstance() = PaymentInformationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payment_information_fragment, container, false)
    }
}