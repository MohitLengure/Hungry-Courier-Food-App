package com.example.food_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.food_app.bottom_fragment.HomeFragment
import com.example.food_app.nav_fragment.ProfileFragment
import com.google.android.material.navigation.NavigationView

class HomeScreen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val onBackPressedCallback = object : OnBackPressedCallback (true)
    {
        override fun handleOnBackPressed() {
           onBackPressedMethod()
        }

    }

    private fun onBackPressedMethod() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            finish()
        }
    }

    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout=findViewById(R.id.drawerLayout)

        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        val header = navigationView.getHeaderView(0)
        val userNameTxt=header.findViewById<TextView>(R.id.userNameTxt)
        val emailTxt = header.findViewById<TextView>(R.id.emailTxt)
        val profileImg = header.findViewById<ImageView>(R.id.profileImg)

        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        //Default Navigation Bar Tab Selected
        replaceFragment(HomeFragment())
        navigationView.setCheckedItem(R.id.bottom_home)


    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment,fragment)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile1 -> {
                replaceFragment(ProfileFragment())
            }

            R.id.nav_share -> {
                Toast.makeText(this,"Share Clicked",Toast.LENGTH_LONG).show()
            }

            R.id.nav_logout -> {
                Toast.makeText(this,"Logout Clicked",Toast.LENGTH_LONG).show()
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}