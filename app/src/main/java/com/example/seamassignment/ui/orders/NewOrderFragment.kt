package com.example.seamassignment.ui.orders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import android.widget.TextView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.seamassignment.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NewOrderFragment : Fragment() {
    //private lateinit var newOrderAdapter: NewOrderAdapter
    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = NewOrderFragment()
    }

    private lateinit var viewModel: NewOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init()
        return inflater.inflate(R.layout.new_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewOrderViewModel::class.java)
        // TODO: Use the ViewModel
        initTabLayout()
    }

    private fun initTabLayout() {
        //newOrderAdapter = NewOrderAdapter(this)
        viewPager = requireView().findViewById(R.id.ViewPagerOrder)
        //viewPager.adapter = newOrderAdapter

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
}


//class NewOrderAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
//
//    override fun getItemCount(): Int = 100
//
//    override fun createFragment(position: Int): Fragment {
//        // Return a NEW fragment instance in createFragment(int)
//        val fragment = NewOrderFragment()
//        fragment.arguments = Bundle().apply {
//            // Our object is just an integer :-P
//            putInt(ARG_OBJECT, position + 1)
//        }
//        return fragment
//    }
//}
//
//private const val ARG_OBJECT = "object"
//
//// Instances of this class are fragments representing a single
//// object in our collection.
//class DemoObjectFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.order_details_fragment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
//            val textView: TextView = view.findViewById(android.R.id.text1)
//            textView.text = getInt(ARG_OBJECT).toString()
//        }
//    }
//}