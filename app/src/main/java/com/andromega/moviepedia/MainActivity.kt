package com.andromega.moviepedia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.andromega.moviepedia.tablayout.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this is for actionbar and image view using instead of title
        val actionBar = getSupportActionBar()
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        addingFragmentsToTabLayout()
        addingActivitiesToBottomMenu()

    }

    fun addingActivitiesToBottomMenu(){

        bottomNavigation.selectedItemId = R.id.bottom_menu_home

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> {

                }
                R.id.bottom_menu_search -> {
                    val intent = Intent(this, SearchActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "search", Toast.LENGTH_LONG).show()
                    finish()
                }
                R.id.bottom_menu_myList -> {
                    val intent = Intent(this, MyListActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "List", Toast.LENGTH_LONG).show()
                    finish()
                }
                R.id.bottom_menu_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "profile", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            true
        }
    }

    fun addingFragmentsToTabLayout(){
        val myViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        myViewPagerAdapter.addFragment(MoviesFragment(), "Movies")
        myViewPagerAdapter.addFragment(TVSeriesFragment(), "TV Series")
        myViewPagerAdapter.addFragment(KidsFragment(), "Kids")
        viewPager.adapter = myViewPagerAdapter
        tabs.setupWithViewPager(viewPager)

    }
}