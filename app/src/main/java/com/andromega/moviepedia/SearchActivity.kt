package com.andromega.moviepedia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.actor_search.ActorSearchAdapter
import com.andromega.moviepedia.actor_search.ActorSearchApi
import com.andromega.moviepedia.actor_search.ActorSearchListModelClass
import com.andromega.moviepedia.actor_search.ActorSearchModelClass
import com.andromega.moviepedia.cast_profile.CastProfileActivity
import com.andromega.moviepedia.movie_search.MovieSearchApi
import com.andromega.moviepedia.movie_search.MovieSearchDetailModelClass
import com.andromega.moviepedia.movie_search.MovieSearchResultsAdapter
import com.andromega.moviepedia.movie_search.MovieSearchResultsListModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesAdapter
import com.andromega.moviepedia.romantic_movies.RomanticMoviesApi
import com.andromega.moviepedia.romantic_movies.RomanticMoviesModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesMovieListModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.andromega.moviepedia.tv_series_info.TvSeriesInfo
import com.andromega.moviepedia.tv_series_search.TvSeriesSearchAdapter
import com.andromega.moviepedia.tv_series_search.TvSeriesSearchApi
import com.andromega.moviepedia.tv_series_search.TvSeriesSearchListModelClass
import com.andromega.moviepedia.tv_series_search.TvSeriesSearchModelClass
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

    private lateinit var tvSeriesSearchResultsAdapter: TvSeriesSearchAdapter
    lateinit var tvSeriesSearchList: List<TvSeriesSearchModelClass>

    private lateinit var actorSearchResultsAdapter: ActorSearchAdapter
    lateinit var actorSearchList: List<ActorSearchModelClass>

    var queryWord:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        //this is for actionbar and image view using instead of title
        val actionBar = supportActionBar
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        addingActivitiesToBottomMenu()

        toggleButtonMovie.isChecked = true
        toggleButtonMovie.background = getDrawable(R.drawable.button_shape_clicked)

        toggleButtonMovie.setOnCheckedChangeListener { compoundButton, b ->
            if (toggleButtonMovie.isChecked){
                toggleButtonMovie.background = getDrawable(R.drawable.button_shape_clicked)
                toggleButtonTvSeries.isChecked = false
                toggleButtonTvSeries.background = getDrawable(R.drawable.button_shape)
                toggleButtonActor.isChecked = false
                toggleButtonActor.background = getDrawable(R.drawable.button_shape)
                movieSearch()
            }
        }

        toggleButtonTvSeries.setOnCheckedChangeListener { compoundButton, b ->
            if (toggleButtonTvSeries.isChecked){
                toggleButtonTvSeries.background = getDrawable(R.drawable.button_shape_clicked)
                toggleButtonMovie.isChecked = false
                toggleButtonMovie.background = getDrawable(R.drawable.button_shape)
                toggleButtonActor.isChecked = false
                toggleButtonActor.background = getDrawable(R.drawable.button_shape)
                tvSeriesSearch()
            }
        }

        toggleButtonActor.setOnCheckedChangeListener { compoundButton, b ->
            if (toggleButtonActor.isChecked){
                toggleButtonActor.background = getDrawable(R.drawable.button_shape_clicked)
                toggleButtonTvSeries.isChecked = false
                toggleButtonTvSeries.background = getDrawable(R.drawable.button_shape)
                toggleButtonMovie.isChecked = false
                toggleButtonMovie.background = getDrawable(R.drawable.button_shape)
                actorSearch()
            }
        }

        if(toggleButtonMovie.isChecked){movieSearch()}
        if(toggleButtonTvSeries.isChecked){tvSeriesSearch()}
        if(toggleButtonActor.isChecked){movieSearch()}



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

    fun tvSeriesSearch(){
        tvSeriesSearchList = emptyList()
        tvSeriesSearchResultsAdapter = TvSeriesSearchAdapter(tvSeriesSearchList) {
                tvSeriesSearchDetailModelClassItem: TvSeriesSearchModelClass ->
                tvSeriesSearchResultClickListener(
                    tvSeriesSearchDetailModelClassItem
                )
        }

        // tv series search results recyclerview
        val tvSeriesSearchLayoutManager = LinearLayoutManager(this)
        tvSeriesSearchLayoutManager.orientation = LinearLayoutManager.VERTICAL
        movieSearchResultsRecyclerview.layoutManager = tvSeriesSearchLayoutManager
        movieSearchResultsRecyclerview.adapter = tvSeriesSearchResultsAdapter

        //tv series search results recyclerview fetch data
        val retrofitTvSeriesSearch = Retrofit.Builder()
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
                val apiTvSeriesSearch = retrofitTvSeriesSearch.create(TvSeriesSearchApi::class.java)
                apiTvSeriesSearch.getTvSeries(queryWord = queryWord).enqueue(object :
                    Callback<TvSeriesSearchListModelClass> {
                    override fun onResponse(
                        call: Call<TvSeriesSearchListModelClass>,
                        response: Response<TvSeriesSearchListModelClass>
                    ) {
                        tvSeriesSearchList = response.body()?.results ?: tvSeriesSearchList
                        tvSeriesSearchResultsAdapter.submitList(tvSeriesSearchList)
                        tvSeriesSearchResultsAdapter.notifyDataSetChanged()
                    }
                    override fun onFailure(call: Call<TvSeriesSearchListModelClass>, t: Throwable) {
                        t.message?.let { Log.e("movie", it) }
                    }
                })
                return false
            }
        })
    }

    fun actorSearch(){
        actorSearchList = emptyList()
        actorSearchResultsAdapter = ActorSearchAdapter(actorSearchList) {
                actorSearchDetailModelClassItem: ActorSearchModelClass ->
            actorSearchResultClickListener(
                actorSearchDetailModelClassItem
            )
        }

        // actor search results recyclerview
        val actorSearchLayoutManager = LinearLayoutManager(this)
        actorSearchLayoutManager.orientation = LinearLayoutManager.VERTICAL
        movieSearchResultsRecyclerview.layoutManager = actorSearchLayoutManager
        movieSearchResultsRecyclerview.adapter = actorSearchResultsAdapter

        //tv series search results recyclerview fetch data
        val retrofitActorSearch = Retrofit.Builder()
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
                val apiActorSearch = retrofitActorSearch.create(ActorSearchApi::class.java)
                apiActorSearch.getPeople(queryWord = queryWord).enqueue(object :
                    Callback<ActorSearchListModelClass> {
                    override fun onResponse(
                        call: Call<ActorSearchListModelClass>,
                        response: Response<ActorSearchListModelClass>
                    ) {
                        actorSearchList = response.body()?.results ?: actorSearchList
                        actorSearchResultsAdapter.submitList(actorSearchList)
                        actorSearchResultsAdapter.notifyDataSetChanged()
                    }
                    override fun onFailure(call: Call<ActorSearchListModelClass>, t: Throwable) {
                        t.message?.let { Log.e("movie", it) }
                    }
                })
                return false
            }
        })
    }

    fun movieSearchResultClickListener(movieSearchDetailModelClass: MovieSearchDetailModelClass){
        val intent = Intent(this, MovieInfoActivity::class.java)
        intent.putExtra("movieName", movieSearchDetailModelClass.movieId)
        startActivity(intent)
    }

    fun tvSeriesSearchResultClickListener(tvSeriesSearchModelClass: TvSeriesSearchModelClass){
        val intent = Intent(this, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", tvSeriesSearchModelClass.movieId)
        startActivity(intent)
    }

    fun actorSearchResultClickListener(actorSearchModelClass: ActorSearchModelClass){
        val intent = Intent(this, CastProfileActivity::class.java)
        intent.putExtra("castId", actorSearchModelClass.movieId)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //Toast.makeText(this, "search", Toast.LENGTH_LONG).show()
        finish()
    }

    fun movieSearch(){
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
}