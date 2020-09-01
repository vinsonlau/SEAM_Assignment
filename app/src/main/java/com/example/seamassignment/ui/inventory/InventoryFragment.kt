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
                val supplierList = arrayOf(R.id.supplier_1,R.id.supplier_2,R.id.supplier_3,R.id.supplier_4,R.id.supplier_5,R.id.supplier_6,R.id.supplier_7,R.id.supplier_8,R.id.supplier_9,R.id.supplier_10,R.id.supplier_11,R.id.supplier_12,R.id.supplier_13,R.id.supplier_14)
                //val btnList = arrayOf(R.id.row_1,R.id.row_2,R.id.row_3,R.id.row_4,R.id.row_5,R.id.row_6,R.id.row_7,R.id.row_8,R.id.row_9,R.id.row_10,R.id.row_11,R.id.row_12,R.id.row_13,R.id.row_14)
                val rowNum = 14

                for(x in 0..rowNum-1){
                    if(x < product!!.size) {
                        root.findViewById<TextView>(idList[x]).setText(product?.get(x)?.productID)
                        root.findViewById<TextView>(categoryList[x]).setText(product?.get(x)?.category)
                        root.findViewById<TextView>(manufacturerList[x]).setText(product?.get(x)?.manufacturer)
                        root.findViewById<TextView>(modelList[x]).setText(product?.get(x)?.model)
                        root.findViewById<TextView>(quantityList[x]).setText(product?.get(x)?.quantity.toString())
                        root.findViewById<TextView>(priceList[x]).setText("RM" + product?.get(x)?.unitPrice.toString() + "0")
                        root.findViewById<TextView>(statusList[x]).setText(product?.get(x)?.status)
                        //Assume only 1 supplier currently
                        root.findViewById<TextView>(supplierList[x]).setText("Mawen Nike")
                    }
                }
                //set on click listener for every rows
                val row1 = root.findViewById<TableRow>(R.id.row_1)
                val row2 = root.findViewById<TableRow>(R.id.row_2)
                val row3 = root.findViewById<TableRow>(R.id.row_3)
                val row4 = root.findViewById<TableRow>(R.id.row_4)
                val row5 = root.findViewById<TableRow>(R.id.row_5)
                val row6 = root.findViewById<TableRow>(R.id.row_6)
                val row7 = root.findViewById<TableRow>(R.id.row_7)
                val row8 = root.findViewById<TableRow>(R.id.row_8)
                val row9 = root.findViewById<TableRow>(R.id.row_9)
                val row10 = root.findViewById<TableRow>(R.id.row_10)
                val row11 = root.findViewById<TableRow>(R.id.row_11)
                val row12 = root.findViewById<TableRow>(R.id.row_12)
                val row13 = root.findViewById<TableRow>(R.id.row_13)
                val row14 = root.findViewById<TableRow>(R.id.row_14)

                val btnOne = root.findViewById<Button>(R.id.btnOne)
                val btnTwo = root.findViewById<Button>(R.id.btnTwo)
                val btnThree = root.findViewById<Button>(R.id.btnThree)
                val btnLeft = root.findViewById<Button>(R.id.btnLeftArrow)
                val btnRight = root.findViewById<Button>(R.id.btnRightArrow)
                var page:Int = 1

                row1.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(0)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(0+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(0+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row2.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(1)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(1+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(1+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row3.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(2+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(2+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row4.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(3)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(3+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(3+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row5.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(4)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(4+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(4+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row6.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(5)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(5+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(5+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row7.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(6)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(6+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(6+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row8.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(7)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(7+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(7+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row9.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(8)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(8+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(8+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row10.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(9)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(9+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(9+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row11.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(10)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(10+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(10+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row12.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(11)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(11+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(11+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row13.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(12)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(12+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(12+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                row14.setOnClickListener {
                    if(page == 1){
                        val product = product?.get(13)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 2){
                        val product = product?.get(13+rowNum)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                    else if(page == 3){
                        val product = product?.get(13+rowNum*2)?.productID
                        view?.findNavController()?.navigate(InventoryFragmentDirections.actionNavigationInventoryToInventoryDetails(product))
                    }
                }

                btnLeft.setOnClickListener {
                    if(page == 2){
                        btnOne.callOnClick()
                    }else if(page == 3){
                        btnTwo.callOnClick()
                    }
                }

                btnRight.setOnClickListener {
                    if(page == 1){
                        btnTwo.callOnClick()
                    }else if(page == 2){
                        btnThree.callOnClick()
                    }
                }

                btnOne.setOnClickListener {
                    clearAll(root,idList, categoryList, manufacturerList, modelList, quantityList, priceList, statusList,supplierList)
                    page = 1
                    for(x in 0..rowNum-1){
                        if(x < product!!.size) {
                            root.findViewById<TextView>(idList[x]).setText(product?.get(x)?.productID)
                            root.findViewById<TextView>(categoryList[x]).setText(product?.get(x)?.category)
                            root.findViewById<TextView>(manufacturerList[x]).setText(product?.get(x)?.manufacturer)
                            root.findViewById<TextView>(modelList[x]).setText(product?.get(x)?.model)
                            root.findViewById<TextView>(quantityList[x]).setText(product?.get(x)?.quantity.toString())
                            root.findViewById<TextView>(priceList[x]).setText("RM" + product?.get(x)?.unitPrice.toString() + "0")
                            root.findViewById<TextView>(statusList[x]).setText(product?.get(x)?.status)
                            //Assume only 1 supplier currently
                            root.findViewById<TextView>(supplierList[x]).setText("Mawen Nike")
                        }
                    }
                }

                btnTwo.setOnClickListener {
                    clearAll(root,idList, categoryList, manufacturerList, modelList, quantityList, priceList, statusList,supplierList)
                    page = 2
                    for(x in 0..rowNum-1){
                        if(x+rowNum < product!!.size) {
                            root.findViewById<TextView>(idList[x]).setText(product?.get(x+rowNum)?.productID)
                            root.findViewById<TextView>(categoryList[x]).setText(product?.get(x+rowNum)?.category)
                            root.findViewById<TextView>(manufacturerList[x]).setText(product?.get(x+rowNum)?.manufacturer)
                            root.findViewById<TextView>(modelList[x]).setText(product?.get(x+rowNum)?.model)
                            root.findViewById<TextView>(quantityList[x]).setText(product?.get(x+rowNum)?.quantity.toString())
                            root.findViewById<TextView>(priceList[x]).setText("RM" + product?.get(x+rowNum)?.unitPrice.toString() + "0")
                            root.findViewById<TextView>(statusList[x]).setText(product?.get(x+rowNum)?.status)
                            //Assume only 1 supplier currently
                            root.findViewById<TextView>(supplierList[x]).setText("Mawen Nike")
                        }
                    }
                }

                btnThree.setOnClickListener {
                    clearAll(root,idList, categoryList, manufacturerList, modelList, quantityList, priceList, statusList,supplierList)
                    page = 3
                    for(x in 0..rowNum-1){
                        if(x+rowNum*2 < product!!.size) {
                            root.findViewById<TextView>(idList[x]).setText(product?.get(x+rowNum*2)?.productID)
                            root.findViewById<TextView>(categoryList[x]).setText(product?.get(x+rowNum*2)?.category)
                            root.findViewById<TextView>(manufacturerList[x]).setText(product?.get(x+rowNum*2)?.manufacturer)
                            root.findViewById<TextView>(modelList[x]).setText(product?.get(x+rowNum*2)?.model)
                            root.findViewById<TextView>(quantityList[x]).setText(product?.get(x+rowNum*2)?.quantity.toString())
                            root.findViewById<TextView>(priceList[x]).setText("RM" + product?.get(x+rowNum*2)?.unitPrice.toString() + "0")
                            root.findViewById<TextView>(statusList[x]).setText(product?.get(x+rowNum*2)?.status)
                            //Assume only 1 supplier currently
                            root.findViewById<TextView>(supplierList[x]).setText("Mawen Nike")
                        }
                    }
                }
            }
        })
    }

    private fun clearAll(root:View, idList:Array<Int>,categoryList:Array<Int>,manufacturerList:Array<Int>,modelList:Array<Int>,quantityList:Array<Int>,priceList:Array<Int>,statusList:Array<Int>,supplierList:Array<Int>){
        val rowNum = 14
        for(x in 0..rowNum-1) {
            root.findViewById<TextView>(idList[x]).setText("")
            root.findViewById<TextView>(categoryList[x]).setText("")
            root.findViewById<TextView>(manufacturerList[x]).setText("")
            root.findViewById<TextView>(modelList[x]).setText("")
            root.findViewById<TextView>(quantityList[x]).setText("")
            root.findViewById<TextView>(priceList[x]).setText("")
            root.findViewById<TextView>(statusList[x]).setText("")
            root.findViewById<TextView>(supplierList[x]).setText("")
        }
    }

    private fun getProductByID(productID: String, root: View){

        RetrofitClient.productInstance.getProductByID(productID).enqueue(object: Callback<Product>{
            override fun onFailure(call: Call<Product>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                val product: Product? = response.body()

                category_3.text = product?.category
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