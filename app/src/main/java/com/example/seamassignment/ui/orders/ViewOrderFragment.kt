package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.seamassignment.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.view_order.*

class ViewOrderFragment: Fragment() {
    private lateinit var viewPager: ViewPager2

    companion object{
        fun newInstance() = ViewOrderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabLayout()
        initSpinner()
        initButton()
    }

    private fun initTabLayout() {
        viewPager = requireView().findViewById(R.id.ViewPagerOrder)

        viewPager.adapter = object : FragmentStateAdapter(this){
            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> {
                        OrderDetailsFragment.newInstance()
                    }
                    1 -> {
                        ShippingInformationFragment.newInstance()
                    }
                    else -> {
                        PaymentInformationFragment.newInstance()
                    }
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }

        val tabLayout = requireView().findViewById<TabLayout>(R.id.tabLayoutOrder)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position){
                0 -> resources.getString(R.string.Order_Details)
                1 -> resources.getString(R.string.Shipping_Information)
                else -> resources.getString(R.string.Payment_Information)
            }
        }.attach()
    }

    private fun initSpinner() {
        val orderStatusArray = arrayOf("Processing", "Packing", "Shipping", "Completed", "Canceled")
        spinnerOrderStatus.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, orderStatusArray)
    }

    private fun initButton(){
        buttonCreateInvoice.setOnClickListener {
            val text = "Invoice generated"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(requireContext(), text, duration)
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
        }
    }
}