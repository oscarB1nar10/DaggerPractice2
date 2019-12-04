package com.example.daggerpractice2.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.daggerpractice2.BaseActivity
import com.example.daggerpractice2.R
import com.example.daggerpractice2.ui.main.post.PostFragments
import com.example.daggerpractice2.ui.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.logout ->{
                sessionManager.logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
      when(item.itemId){
          R.id.nav_profile -> {
              Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileFragment)}
          R.id.nav_posts -> {
              Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postFragments)
          }
          android.R.id.home ->  {
              if(drawer_layout.isDrawerOpen(GravityCompat.START)){
                drawer_layout.closeDrawer(GravityCompat.START)
                  return true
              }else{
                  return false
              }
          }
      }

        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return  true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment),drawer_layout)
    }
}