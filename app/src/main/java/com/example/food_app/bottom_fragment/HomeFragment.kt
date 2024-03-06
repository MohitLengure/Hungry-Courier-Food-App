package com.example.food_app.bottom_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.food_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val bottomNavigationView= view.findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId){
                R.id.bottom_home ->{
                    replaceFragement(HomeFragment())
                    activity?.title="Home"
                }
                R.id.bottom_order ->{
                    replaceFragement(OrderFragment())
                    activity?.title="Order"

                }
                R.id.bottom_favorites ->{
                    replaceFragement(FavoritesFragment())
                    activity?.title="Favorites"

                }
                R.id.bottom_cart ->{
                    replaceFragement(CartFragment())
                    activity?.title="Cart"

                }


            }
            true
        }
        replaceFragement(HomeFragment())
        activity?.title="Home"
        bottomNavigationView.selectedItemId=R.id.bottom_home


        val addFab =view.findViewById<FloatingActionButton>(R.id.addFabBtn)
        addFab.setOnClickListener{
            Toast.makeText(context,"Add Clicked",Toast.LENGTH_LONG).show();

        }
        return view
    }
private fun replaceFragement(fragment: Fragment){
    parentFragmentManager
        .beginTransaction()
        .replace(R.id.bottomFragment,fragment)
        .commit()
}
    }
