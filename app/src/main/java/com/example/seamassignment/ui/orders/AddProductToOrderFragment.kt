package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seamassignment.Model.Customer
import com.example.seamassignment.Model.Product
import com.example.seamassignment.Model.Staff
import com.example.seamassignment.R
import com.example.seamassignment.RetrofitClient
import kotlinx.android.synthetic.main.add_product_to_order_fragment.*
import kotlinx.android.synthetic.main.new_order.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductToOrderFragment: Fragment() {
    companion object{
        fun newInstance() = AddProductToOrderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_product_to_order_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButtons()
        initSpinner()
    }

    private fun initSpinner() {
        //val productArray = arrayOf("Abu","Praveen","Sathya","Yogesh","Ram")
        //spinnerProduct.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, productArray)

        RetrofitClient.productInstance.getProduct().enqueue(object :
            Callback<ArrayList<Product>> {
            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                val productArrayList: ArrayList<Product>? = response.body()
                val productNameArrayList = arrayListOf<String>()

                //filter the staff name list based on the staff role
                if (productArrayList != null) {
                    for (product in productArrayList) {
                        productNameArrayList.add(product.manufacturer + " " +  product.category + " " + product.model)
                    }

                    spinnerProduct.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        productNameArrayList!!
                    )
                }
            }

            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.d("Retrieve product list", "Failed")
            }
        })
    }

    private fun initButtons() {
        buttonAdd.setOnClickListener {
            it?.findNavController()?.navigate(R.id.action_addProductToOrderFragment_to_newOrderFragment)
        }

        imageButtonAdd.setOnClickListener {
            if(editTextNumberQuantity.text.isNotBlank()){
                editTextNumberQuantity.setText(
                    (editTextNumberQuantity.text.toString().toInt() + 1).toString()
                )
            }
            else{
                editTextNumberQuantity.setText("1")
            }
        }

        imageButtonMinus.setOnClickListener {
            if(editTextNumberQuantity.text.isNotBlank()){
                val quantity: Int = editTextNumberQuantity.text.toString().toInt()

                if(quantity > 0){
                    editTextNumberQuantity.setText((quantity - 1).toString())
                }
            }
            else{
                editTextNumberQuantity.setText("0")
            }
        }
    }
}