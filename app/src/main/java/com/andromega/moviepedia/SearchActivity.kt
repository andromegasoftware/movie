package com.andromega.moviepedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andromega.moviepedia.tablayout.KidsFragment
import com.andromega.moviepedia.tablayout.MoviesFragment
import com.andromega.moviepedia.tablayout.TVSeriesFragment
import com.andromega.moviepedia.tablayout.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //this is for actionbar and image view using instead of title
        val actionBar = getSupportActionBar()
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        addingActivitiesToBottomMenu()
    }

    fun addingActivitiesToBottomMenu(){

        bottomNavigation.selectedItemId = R.id.bottom_menu_search
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "search", Toast.LENGTH_LONG).show()
                    finish()
                }
                R.id.bottom_menu_search -> {

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
}