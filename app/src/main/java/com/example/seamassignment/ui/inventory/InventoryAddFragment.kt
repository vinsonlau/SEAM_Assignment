package com.example.seamassignment.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import kotlinx.android.synthetic.main.inventory_add.*
import kotlinx.android.synthetic.main.inventory_page.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import kotlin.collections.ArrayList

class InventoryAddFragment : Fragment() {

    private lateinit var notificationsViewModel: InventoryViewModel
    private val list = ArrayList<CustomerOrders>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.inventory_add, container, false)

        val addProduct = root.findViewById<Button>(R.id.btnAdd)
        val backInAdd = root.findViewById<Button>(R.id.btnBackinAdd)

        addProduct.setOnClickListener{
            createProduct()
        }

        backInAdd.setOnClickListener {
            view?.findNavController()?.navigate(R.id.navigation_inventory)
        }

        return root
    }

    private fun createProduct(){
        val product = Product("PTest",
            modelAdd.text.toString(),
            manufacturerAdd.text.toString(),
            categoryAdd.selectedItem.toString(),
            descriptionAdd.text.toString(),
            unitPriceAdd.text.toString().toDouble(),
            statusAdd.text.toString(),
            quantityAdd.text.toString().toInt(),
            remarksAdd.text.toString(),
            targetLevelAdd.text.toString().toInt(),
            reorderLevelAdd.text.toString().toInt(),
            locationAdd1.text.toString() + "|" +
                    locationAdd2.text.toString() + "|" +
                    locationAdd3.text.toString(),
            "STest"
        )
        RetrofitClient.productInstance.createProduct(product).enqueue(object:Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                view?.findNavController()?.navigate(R.id.navigation_inventory)
            }
        })
    }

}

