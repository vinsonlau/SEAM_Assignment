package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
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
        /*val customerOrdersExample = CustomerOrders("OR1001",
        "C1001",
        "S1001",
        "Now",
        "P1003",
        6,
        0.0)*/
        //createCustomerOrder(customerOrdersExample)

        getProductByID("P006",root)
        getCustomerOrderByID("OR1001",root)
        getProduct()

        val row_1_click = root.findViewById<TableRow>(R.id.row_1)
        row_1_click.setOnClickListener {
            view?.findNavController()?.navigate(R.id.inventory_details)
        }

        return root
    }

    public fun onClick(view:View){
        val clicked_id = view.id
    }

    private fun getProduct(){
        RetrofitClient.productInstance.getProduct().enqueue(object: Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                val product: ArrayList<Product>? = response.body()

                //for(x in 1..14){
                    id_1.text = product?.get(0)?.productID
                    category_1.text = product?.get(0)?.category
                    manufacturer_1.text = product?.get(0)?.manufacturer
                    model_1.text = product?.get(0)?.model
                    quantity_1.text = product?.get(0)?.quantity.toString()
                    price_1.text = product?.get(0)?.unitPrice.toString()
                    status_1.text = product?.get(0)?.status
                //}
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

                category_2.text = product?.get(0)?.category
            }
        })
    }

    private fun getCustomerOrderByID(productID: String, root: View){

        RetrofitClient.customerOrderInstance.getCustomerOrdersByID(productID).enqueue(object: Callback<ArrayList<CustomerOrders>>{
            override fun onFailure(call: Call<ArrayList<CustomerOrders>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ArrayList<CustomerOrders>>,
                response: Response<ArrayList<CustomerOrders>>
            ) {
                val product: ArrayList<CustomerOrders>? = response.body()

                category_2.text = product?.get(0)?.date


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