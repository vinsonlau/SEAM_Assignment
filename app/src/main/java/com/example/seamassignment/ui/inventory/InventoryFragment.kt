package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seamassignment.Model.CustomerOrders
import com.example.seamassignment.R
import com.example.seamassignment.RetrofitClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seamassignment.Model.Product
import kotlinx.android.synthetic.main.inventory_page.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import kotlin.collections.ArrayList

class InventoryFragment : Fragment() {

    private lateinit var notificationsViewModel: InventoryViewModel
    private val list = ArrayList<CustomerOrders>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.inventory_page, container, false)
        getProduct(root)

        val addProduct = root.findViewById<Button>(R.id.btnAddNewProduct)

        addProduct.setOnClickListener{
            view?.findNavController()?.navigate(R.id.inventoryAddFragment)
        }


        return root
    }

    private fun getProduct(root: View){
        RetrofitClient.productInstance.getProduct().enqueue(object: Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                val product: ArrayList<Product>? = response.body()

                //for(x in 1..14){
                val idList = arrayOf(R.id.id_1, R.id.id_2,R.id.id_3,R.id.id_4,R.id.id_5,R.id.id_6,R.id.id_7,R.id.id_8,R.id.id_9,R.id.id_10,R.id.id_11,R.id.id_12,R.id.id_13,R.id.id_14)
                val categoryList = arrayOf(R.id.category_1, R.id.category_2,R.id.category_3,R.id.category_4,R.id.category_5,R.id.category_6,R.id.category_7,R.id.category_8,R.id.category_9,R.id.category_10,R.id.category_11,R.id.category_12,R.id.category_13,R.id.category_14)
                val manufacturerList = arrayOf(R.id.manufacturer_1, R.id.manufacturer_2,R.id.manufacturer_3,R.id.manufacturer_4,R.id.manufacturer_5,R.id.manufacturer_6,R.id.manufacturer_7,R.id.manufacturer_8,R.id.manufacturer_9,R.id.manufacturer_10,R.id.manufacturer_11,R.id.manufacturer_12,R.id.manufacturer_13,R.id.manufacturer_14)
                val modelList = arrayOf(R.id.model_1, R.id.model_2,R.id.model_3,R.id.model_4,R.id.model_5,R.id.model_6,R.id.model_7,R.id.model_8,R.id.model_9,R.id.model_10,R.id.model_11,R.id.model_12,R.id.model_13,R.id.model_14)
                val quantityList = arrayOf(R.id.quantity_1, R.id.quantity_2,R.id.quantity_3,R.id.quantity_4,R.id.quantity_5,R.id.quantity_6,R.id.quantity_7,R.id.quantity_8,R.id.quantity_9,R.id.quantity_10,R.id.quantity_11,R.id.quantity_12,R.id.quantity_13,R.id.quantity_14)
                val priceList = arrayOf(R.id.price_1, R.id.price_2,R.id.price_3,R.id.price_4,R.id.price_5,R.id.price_6,R.id.price_7,R.id.price_8,R.id.price_9,R.id.price_10,R.id.price_11,R.id.price_12,R.id.price_13,R.id.price_14)
                val statusList = arrayOf(R.id.status_1, R.id.status_2,R.id.status_3,R.id.status_4,R.id.status_5,R.id.status_6,R.id.status_7,R.id.status_8,R.id.status_9,R.id.status_10,R.id.status_11,R.id.status_12,R.id.status_13,R.id.status_14)
                val btnList = arrayOf(R.id.row_1,R.id.row_2,R.id.row_3,R.id.row_4,R.id.row_5,R.id.row_6,R.id.row_7,R.id.row_8,R.id.row_9,R.id.row_10,R.id.row_11,R.id.row_12,R.id.row_13,R.id.row_14)

                for(x in 0..idList.size-1){
                    if(x < product!!.size) {
                        root.findViewById<TextView>(idList[x]).setText(product?.get(x)?.productID)
                        root.findViewById<TextView>(categoryList[x]).setText(product?.get(x)?.category)
                        root.findViewById<TextView>(manufacturerList[x]).setText(product?.get(x)?.manufacturer)
                        root.findViewById<TextView>(modelList[x]).setText(product?.get(x)?.model)
                        root.findViewById<TextView>(quantityList[x]).setText(product?.get(x)?.quantity.toString())
                        root.findViewById<TextView>(priceList[x]).setText("RM" + product?.get(x)?.unitPrice.toString() + "0")
                        root.findViewById<TextView>(statusList[x]).setText(product?.get(x)?.status)
                    }
                }

                val tr = root.findViewById<TableRow>(R.id.row_1)
                tr.setOnClickListener {
                    val product = product?.get(0)?.productID
                    view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                }

            }
        })
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

                category_3.text = product?.get(0)?.category
            }
        })
    }

    private fun createProduct(product:Product){
        RetrofitClient.productInstance.createProduct(product).enqueue(object:Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }
        })
    }
}