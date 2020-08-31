package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.Person.fromBundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seamassignment.Model.CustomerOrders
import com.example.seamassignment.R
import com.example.seamassignment.RetrofitClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seamassignment.Model.Product
import kotlinx.android.synthetic.main.fragment_inventory.*
import kotlinx.android.synthetic.main.inventory_add.*
import kotlinx.android.synthetic.main.inventory_page.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import kotlin.collections.ArrayList

class InventoryDetailsFragment : Fragment() {

    private lateinit var notificationsViewModel: InventoryViewModel
    private val list = ArrayList<CustomerOrders>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inventory, container, false)
        val args = InventoryDetailsFragmentArgs.fromBundle(requireArguments())
        val productID = root.findViewById<EditText>(R.id.etProductID).toString()
        getProductByID(productID,root)
        return root
    }

    private fun getProductByID(productID: String, root: View){

        RetrofitClient.productInstance.getProductByID(productID).enqueue(object: Callback<ArrayList<Product>>{
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                val product: ArrayList<Product>? = response.body()

                //retrieve id
                root.findViewById<EditText>(R.id.etProductID).setText(product?.get(0)?.productID)
                //retrieve category
                val category = root.findViewById<EditText>(R.id.etProductID)

                if(product?.get(0)?.category.toString() == "Bag")
                    category.setSelection(0)
                else if(product?.get(0)?.category.toString() == "Bottom")
                    category.setSelection(1)
                else if(product?.get(0)?.category.toString() == "Cap")
                    category.setSelection(2)
                else if(product?.get(0)?.category.toString() == "Shoes")
                    category.setSelection(3)
                else if(product?.get(0)?.category.toString() == "Socks")
                    category.setSelection(4)
                else if(product?.get(0)?.category.toString() == "Top")
                    category.setSelection(5)

                //need to split ","
                root.findViewById<EditText>(R.id.etLocation1).setText(product?.get(0)?.location)
                root.findViewById<EditText>(R.id.etManufacturer).setText(product?.get(0)?.manufacturer)
                //lack of supplier yet
                //root.findViewById<EditText>(R.id.etSupplier).setText(product?.get(0)?.supplier)
                root.findViewById<EditText>(R.id.etModel).setText(product?.get(0)?.model)
                root.findViewById<EditText>(R.id.etDescription).setText(product?.get(0)?.description)
                root.findViewById<EditText>(R.id.etRemarks).setText(product?.get(0)?.remark)
                root.findViewById<EditText>(R.id.etReorderLevel).setText(product?.get(0)?.reorderStockLevel.toString())
                root.findViewById<EditText>(R.id.etTargetLevel).setText(product?.get(0)?.targetStockLevel.toString())
                root.findViewById<EditText>(R.id.etStatus).setText(product?.get(0)?.status)
                root.findViewById<EditText>(R.id.etQuantity).setText(product?.get(0)?.quantity.toString())


            }
        })
    }

}