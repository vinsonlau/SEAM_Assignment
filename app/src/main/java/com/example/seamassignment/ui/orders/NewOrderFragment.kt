package com.example.seamassignment.ui.orders

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.seamassignment.Model.Customer
import com.example.seamassignment.Model.Staff
import com.example.seamassignment.R
import com.example.seamassignment.RetrofitClient
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.new_order.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class NewOrderFragment : Fragment() {
    //private lateinit var newOrderAdapter: NewOrderAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var viewModel: NewOrderViewModel

    companion object {
        fun newInstance() = NewOrderFragment()
    }

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
        //initTabLayout()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initOrderDate()
        initButtons()
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

    private fun initSpinner(){
        //val customerArray = arrayOf("Abu","Praveen","Sathya","Yogesh","Ram")
        //spinnerCustomer.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, customerArray)

        //val salesPersonArray = arrayOf("Chew Hao Xian","Lim Ruen Feng","Marven Lim Chin Nien","Vinson Lau Wei Ping")
        //spinnerSalesperson.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, salesPersonArray)

        //get customer list from database and fill up the spinner
        RetrofitClient.customerInstance.getCustomerList().enqueue(object: Callback<ArrayList<Customer>>{
            override fun onResponse(
                call: Call<ArrayList<Customer>>,
                response: Response<ArrayList<Customer>>
            ) {
                val customerArrayList: ArrayList<Customer>? = response.body()
                spinnerCustomer.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, customerArrayList!!)
                spinnerCustomer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val customer = parent.getItemAtPosition(position) as Customer
                        textViewEmail.text = customer.email
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // another interface callback
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Customer>>, t: Throwable) {
                Log.d("Retrieve customer list", "Failed")
            }
        })

        //get staff list from database and fill up the spinner
        RetrofitClient.staffInstance.getStaffList().enqueue(object: Callback<ArrayList<Staff>>{
            override fun onResponse(
                call: Call<ArrayList<Staff>>,
                response: Response<ArrayList<Staff>>
            ) {
                val staffArrayList: ArrayList<Staff>? = response.body()
                val staffArrayListFiltered = arrayListOf<Staff>()

                //filter the staff name list based on the staff role
                if(staffArrayList != null){
                    for(staff in staffArrayList){
                        if (staff.staffRole.equals("SR03")){ //SR03 = Salesperson
                            staffArrayListFiltered.add(staff)
                        }
                    }

                    spinnerSalesperson.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, staffArrayListFiltered!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<Staff>>, t: Throwable) {
                Log.d("Retrieve staff list", "Failed")
            }
        })

        //test
//        RetrofitClient.staffInstance.getStaffByID("S001").enqueue(object: Callback<Staff>{
//            override fun onResponse(call: Call<Staff>, response: Response<Staff>) {
//                val staff: Staff? = response.body()
//                Log.d("Get Staff by ID", staff.toString())
//            }
//
//            override fun onFailure(call: Call<Staff>, t: Throwable) {
//                Log.d("Get Staff by ID", "Failed")
//            }
//
//        })
    }

    private fun initButtons(){
        buttonAddProduct.setOnClickListener {
            it?.findNavController()?.navigate(R.id.action_newOrderFragment_to_addProductToOrderFragment)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initOrderDate(){
        val dateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        textViewOrderDate.text = dateString
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