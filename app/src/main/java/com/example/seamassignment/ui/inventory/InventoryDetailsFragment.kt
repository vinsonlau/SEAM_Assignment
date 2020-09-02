package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.Person.fromBundle
import androidx.core.view.isVisible
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

        root.findViewById<Spinner>(R.id.etCategory).isEnabled = false


        getProductByID(args.productID.toString(),root)

        val backInDetails = root.findViewById<Button>(R.id.btnBackInDetails)
        val editInDetails = root.findViewById<Button>(R.id.btnEditInDetails)

        backInDetails.setOnClickListener {
            view?.findNavController()?.navigate(R.id.navigation_inventory)
        }

        editInDetails.setOnClickListener {
            editInDetails.visibility = View.GONE
            backInDetails.visibility = View.GONE
            btnUpdateInDetails.visibility = View.VISIBLE
            btnDeleteInDetails.visibility = View.VISIBLE
            btnBackInDetails2.visibility = View.VISIBLE

            val backInDetails2 = root.findViewById<Button>(R.id.btnBackInDetails2)
            val deleteInDetails = root.findViewById<Button>(R.id.btnDeleteInDetails)
            val updateInDetails = root.findViewById<Button>(R.id.btnUpdateInDetails)

            root.findViewById<Spinner>(R.id.etCategory).isEnabled = true
            root.findViewById<EditText>(R.id.etLocation1).isEnabled = true
            root.findViewById<EditText>(R.id.etLocation2).isEnabled = true
            root.findViewById<EditText>(R.id.etLocation3).isEnabled = true
            root.findViewById<EditText>(R.id.etManufacturer).isEnabled = true
            root.findViewById<EditText>(R.id.etSupplier).isEnabled = true
            root.findViewById<EditText>(R.id.etModel).isEnabled = true
            root.findViewById<EditText>(R.id.etDescription).isEnabled = true
            root.findViewById<EditText>(R.id.etRemarks).isEnabled = true
            root.findViewById<EditText>(R.id.etReorderLevel).isEnabled = true
            root.findViewById<EditText>(R.id.etTargetLevel).isEnabled = true
            root.findViewById<EditText>(R.id.etStatus).isEnabled = true
            root.findViewById<EditText>(R.id.etQuantity).isEnabled = true
            root.findViewById<EditText>(R.id.etUnitPrice).isEnabled = true

            backInDetails2.setOnClickListener {
                editInDetails.visibility = View.VISIBLE
                backInDetails.visibility = View.VISIBLE
                btnUpdateInDetails.visibility = View.GONE
                btnDeleteInDetails.visibility = View.GONE
                btnBackInDetails2.visibility = View.GONE

                root.findViewById<Spinner>(R.id.etCategory).isEnabled = false
                root.findViewById<EditText>(R.id.etLocation1).isEnabled = false
                root.findViewById<EditText>(R.id.etLocation2).isEnabled = false
                root.findViewById<EditText>(R.id.etLocation3).isEnabled = false
                root.findViewById<EditText>(R.id.etManufacturer).isEnabled = false
                root.findViewById<EditText>(R.id.etSupplier).isEnabled = false
                root.findViewById<EditText>(R.id.etModel).isEnabled = false
                root.findViewById<EditText>(R.id.etDescription).isEnabled = false
                root.findViewById<EditText>(R.id.etRemarks).isEnabled = false
                root.findViewById<EditText>(R.id.etReorderLevel).isEnabled = false
                root.findViewById<EditText>(R.id.etTargetLevel).isEnabled = false
                root.findViewById<EditText>(R.id.etStatus).isEnabled = false
                root.findViewById<EditText>(R.id.etQuantity).isEnabled = false
                root.findViewById<EditText>(R.id.etUnitPrice).isEnabled = false
            }

            deleteInDetails.setOnClickListener{
                deleteProductByID(args.productID.toString(), root)
                view?.findNavController()?.navigate(R.id.navigation_inventory)
            }

            updateInDetails.setOnClickListener {
                /*deleteProductByID(args.productID.toString(),root)
                createProduct(args.productID.toString())*/
                val product = Product(args.productID.toString(),
                    etModel.text.toString(),
                    etManufacturer.text.toString(),
                    etCategory.selectedItem.toString(),
                    etDescription.text.toString(),
                    etUnitPrice.text.toString().toDouble(),
                    etStatus.text.toString(),
                    etQuantity.text.toString().toInt(),
                    etRemarks.text.toString(),
                    etTargetLevel.text.toString().toInt(),
                    etReorderLevel.text.toString().toInt(),
                    etLocation1.text.toString() + "|" +
                            etLocation2.text.toString() + "|" +
                            etLocation3.text.toString(),
                    "STest"
                )

                updateProductByID(args.productID.toString(), product)
                view?.findNavController()?.navigate(R.id.navigation_inventory)
            }
        }

        return root
    }

    private fun getProductByID(productID: String, root: View){
        RetrofitClient.productInstance.getProductByID(productID).enqueue(object: Callback<Product>{
            override fun onFailure(call: Call<Product>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                root.findViewById<EditText>(R.id.etRemarks).setText("Success")

                val product: Product? = response.body()

                //retrieve id
                root.findViewById<EditText>(R.id.etProductID).setText(product?.productID)
                //retrieve category
                val category = root.findViewById<Spinner>(R.id.etCategory)

                if(product?.category.toString() == "Bag")
                    category.setSelection(1)
                else if(product?.category.toString() == "Bottom")
                    category.setSelection(2)
                else if(product?.category.toString() == "Cap")
                    category.setSelection(3)
                else if(product?.category.toString() == "Shoes")
                    category.setSelection(4)
                else if(product?.category.toString() == "Socks")
                    category.setSelection(5)
                else if(product?.category.toString() == "Top")
                    category.setSelection(6)

                //need to split "|"
                var str = product?.location
                val location = str?.split("|")!!.toTypedArray()

                root.findViewById<EditText>(R.id.etLocation1).setText(location[0])
                root.findViewById<EditText>(R.id.etLocation2).setText(location[1])
                root.findViewById<EditText>(R.id.etLocation3).setText(location[2])
                root.findViewById<EditText>(R.id.etManufacturer).setText(product?.manufacturer)
                //lack of supplier yet
                //root.findViewById<EditText>(R.id.etSupplier).setText(product?.get(0)?.supplier)
                root.findViewById<EditText>(R.id.etModel).setText(product?.model)
                root.findViewById<EditText>(R.id.etDescription).setText(product?.description)
                root.findViewById<EditText>(R.id.etRemarks).setText(product?.remark)
                root.findViewById<EditText>(R.id.etReorderLevel).setText(product?.reorderStockLevel.toString())
                root.findViewById<EditText>(R.id.etTargetLevel).setText(product?.targetStockLevel.toString())
                root.findViewById<EditText>(R.id.etStatus).setText(product?.status)
                root.findViewById<EditText>(R.id.etQuantity).setText(product?.quantity.toString())
                root.findViewById<EditText>(R.id.etUnitPrice).setText(product?.unitPrice.toString())
            }
        })
    }

    private fun deleteProductByID(productID: String, root: View){

        RetrofitClient.productInstance.deleteProductByID(productID).enqueue(object: Callback<Product>{
            override fun onFailure(call: Call<Product>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
            }
        })
    }

    private fun updateProductByID(productID: String, product:Product){

        RetrofitClient.productInstance.updateProductByID(productID,product).enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }
        })
    }

    /*private fun createProduct(prodID:String){
        val product = Product(prodID,
            etModel.text.toString(),
            etManufacturer.text.toString(),
            etCategory.selectedItem.toString(),
            etDescription.text.toString(),
            etUnitPrice.text.toString().toDouble(),
            etStatus.text.toString(),
            etQuantity.text.toString().toInt(),
            etRemarks.text.toString(),
            etTargetLevel.text.toString().toInt(),
            etReorderLevel.text.toString().toInt(),
            etLocation1.text.toString() + "|" +
                    etLocation2.text.toString() + "|" +
                    etLocation3.text.toString(),
            "STest"
        )
        RetrofitClient.productInstance.createProduct(product).enqueue(object:Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                view?.findNavController()?.navigate(R.id.navigation_inventory)
            }
        })
    }*/

}