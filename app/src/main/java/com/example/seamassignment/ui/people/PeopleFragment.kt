package com.example.seamassignment.ui.people

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import com.example.seamassignment.R
import kotlinx.android.synthetic.main.fragment_people.*
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

class PeopleFragment : Fragment() {

    private lateinit var homeViewModel: PeopleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_people, container, false)

        val btnLogin = root.findViewById<Button>(R.id.btnLogin)
        val btnLogout = root.findViewById<Button>(R.id.btnLogout)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val navView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navView.menu.findItem(R.id.navigation_orders).isEnabled = false
        navView.menu.findItem(R.id.navigation_inventory).isEnabled = false

        btnLogin.setOnClickListener {
            if(etUsername.text.toString() == "admin" && etPassword.text.toString() == "admin"){
                tvSuccessLogin.visibility = View.VISIBLE
                btnLogout.visibility = View.VISIBLE

                tvUsername.visibility = View.GONE
                tvPassword.visibility = View.GONE
                etUsername.visibility = View.GONE
                etPassword.visibility = View.GONE
                btnLogin.visibility = View.GONE

                navView.menu.findItem(R.id.navigation_orders).isEnabled = true
                navView.menu.findItem(R.id.navigation_inventory).isEnabled = true
            }
        }

        btnLogout.setOnClickListener {
            tvSuccessLogin.visibility = View.GONE
            btnLogout.visibility = View.GONE

            tvUsername.visibility = View.VISIBLE
            tvPassword.visibility = View.VISIBLE
            etUsername.visibility = View.VISIBLE
            etPassword.visibility = View.VISIBLE
            btnLogin.visibility = View.VISIBLE

            navView.menu.findItem(R.id.navigation_orders).isEnabled = false
            navView.menu.findItem(R.id.navigation_inventory).isEnabled = false
        }
        //activity?.invalidateOptionsMenu()
        return root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.bottom_nav_menu, menu)
        val test = menu.findItem(R.id.navigation_orders)

        test.setEnabled(false)
        test.setTitle("hola")
        test.setIcon(null)
        tvUsername.setText(test.toString())
        return super.onCreateOptionsMenu(menu, inflater)
    }*/

   /* private fun updateMenuTitles(menu: MenuItem){
        menu.title = "hola"
        menu.setIcon(null)
        tvUsername.setText(menu.toString())
    }*/

    /*override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val test = menu.findItem(R.id.navigation_inventory)

        test.setTitle("hola")
        test.setVisible(false)
        etUsername.setText(test.toString())
    }*/
}