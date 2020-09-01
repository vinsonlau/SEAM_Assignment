package com.example.seamassignment.ui.people

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import com.example.seamassignment.R
import kotlinx.android.synthetic.main.fragment_people.*

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

        btnLogin.setOnClickListener {
            if(etUsername.text.toString() == "admin" && etPassword.text.toString() == "admin"){
                tvSuccessLogin.visibility = View.VISIBLE
                btnLogout.visibility = View.VISIBLE

                tvUsername.visibility = View.GONE
                tvPassword.visibility = View.GONE
                etUsername.visibility = View.GONE
                etPassword.visibility = View.GONE
                btnLogin.visibility = View.GONE
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
        }
        return root

    }

}