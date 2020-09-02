package com.example.seamassignment.ui.orders

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.seamassignment.R
import kotlinx.android.synthetic.main.add_product_to_order_fragment.*

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