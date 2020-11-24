package com.andromega.moviepedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.movie_search.MovieSearchApi
import com.andromega.moviepedia.movie_search.MovieSearchDetailModelClass
import com.andromega.moviepedia.movie_search.MovieSearchResultsAdapter
import com.andromega.moviepedia.movie_search.MovieSearchResultsListModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesAdapter
import com.andromega.moviepedia.romantic_movies.RomanticMoviesApi
import com.andromega.moviepedia.romantic_movies.RomanticMoviesModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesMovieListModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private lateinit var movieSearchResultsAdapter: MovieSearchResultsAdapter
    lateinit var movieSearchList: List<MovieSearchDetailModelClass>
    var queryWord:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //this is for actionbar and image view using instead of title
        val actionBar = getSupportActionBar()
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        addingActivitiesToBottomMenu()

        movieSearchList = emptyList()
        movieSearchResultsAdapter = MovieSearchResultsAdapter(movieSearchList, {movieSearchDetailModelClassItem: MovieSearchDetailModelClass -> movieSearchResultClickListener(movieSearchDetailModelClassItem)})

        // movies search results recyclerview
        val movieSearchLayoutManager = LinearLayoutManager(this)
        movieSearchLayoutManager.orientation = LinearLayoutManager.VERTICAL
        movieSearchResultsRecyclerview.layoutManager = movieSearchLayoutManager
        movieSearchResultsRecyclerview.adapter = movieSearchResultsAdapter

        //movie search results recyclerview fetch data
        val retrofitMovieSearch = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val searchView = findViewById<SearchView>(R.id.movieSearchView)
        searchView.isActivated = true
        searchView.isSelected = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //searchView.clearFocus()

                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {

                if (p0 != null) {
                    queryWord = p0
                }

                val apiMovieSearch = retrofitMovieSearch.create(MovieSearchApi::class.java)
                apiMovieSearch.getMoviesAndSeriesAndPeople(queryWord = queryWord).enqueue(object :
                    Callback<MovieSearchResultsListModelClass> {
                    override fun onResponse(
                        call: Call<MovieSearchResultsListModelClass>,
                        response: Response<MovieSearchResultsListModelClass>
                    ) {
                        movieSearchList = response.body()?.results ?: movieSearchList
                        movieSearchResultsAdapter.submitList(movieSearchList)
                        movieSearchResultsAdapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<MovieSearchResultsListModelClass>, t: Throwable) {
                        t.message?.let { Log.e("movie", it) }
                    }
                })
                return false
            }
        })
    }

    fun addingActivitiesToBottomMenu(){

        bottomNavigation.selectedItemId = R.id.bottom_menu_search
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_menu_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this, "search", Toast.LENGTH_LONG).show()
                    finish()
                }
                R.id.bottom_menu_search -> {

                }
                R.id.bottom_menu_myList -> {
                    val intent = Intent(this, MyListActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this, "List", Toast.LENGTH_LONG).show()
                    finish()
                }
                R.id.bottom_menu_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    //Toast.makeText(this, "profile", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            true
        }
    }

    fun movieSearchResultClickListener(movieSearchDetailModelClass: MovieSearchDetailModelClass){
        val intent = Intent(this, MovieInfoActivity::class.java)
        intent.putExtra("movieName", movieSearchDetailModelClass.movieId)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this, "search", Toast.LENGTH_LONG).show()
        finish()
    }
}