package com.andromega.moviepedia.cast_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.cast_known_movies.Cast
import com.andromega.moviepedia.cast_known_movies.CastKnownModelClass
import com.andromega.moviepedia.cast_known_movies.CastKnownMoviesAdapter
import com.andromega.moviepedia.cast_known_movies.CastKnownMoviesApi
import com.andromega.moviepedia.data.vo.Genre
import com.andromega.moviepedia.movie_cast.MovieCastListApi
import com.andromega.moviepedia.movie_cast.MovieCastListModelClass
import com.andromega.moviepedia.movie_cast.MovieCastModelClass
import com.andromega.moviepedia.movie_cast.MovieCastRecyclerviewAdapter
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cast_profile.*
import kotlinx.android.synthetic.main.activity_movie_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CastProfileActivity : AppCompatActivity() {

    var castId : String = ""
    lateinit var castMoviesList: List<Cast>
    private lateinit var castKnownMoviesAdapter: CastKnownMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cast_profile)

        //this is for actionbar and image view using instead of title
        val actionBar = getSupportActionBar()
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        castId = intent.getIntExtra("castId", 1).toString()
        castMoviesList = emptyList()

        castKnownMoviesAdapter = CastKnownMoviesAdapter(castMoviesList, {castMoviesNameClassItem: Cast -> castKonwnMoviesClickListener(castMoviesNameClassItem)})

        //cast known movies list recyclerview
        val castKnownMoviesListlayoutManager = LinearLayoutManager(this)
        castKnownMoviesListlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        castProfileKnownMoviesRecyclerview.layoutManager = castKnownMoviesListlayoutManager
        castProfileKnownMoviesRecyclerview.adapter = castKnownMoviesAdapter

        //cast known movies list recyclerview fetch data
        val retrofitCastKnownMoviesList = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCastKnownMoviesList = retrofitCastKnownMoviesList.create(CastKnownMoviesApi::class.java)
        apiCastKnownMoviesList.getCastKnownMovies(castId = castId.toString()).enqueue(object : Callback<CastKnownModelClass>{
            override fun onResponse(
                call: Call<CastKnownModelClass>,
                response: Response<CastKnownModelClass>
            ) {
                castMoviesList = response.body()?.cast ?: castMoviesList
                /*Log.e("movies vote count: ",
                    castMoviesList.sortedByDescending { it.voteCount }.toString()
                )*/
                castKnownMoviesAdapter.submitList(castMoviesList.sortedByDescending { it.voteCount })
                castKnownMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CastKnownModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

        //cast profile fetching data
        val retrofitCastProfile = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCastProfile = retrofitCastProfile.create(CastProfileApi::class.java)
        apiCastProfile.getCastProfileData(castId = castId.toString()).enqueue(object :
            Callback<CastProfileModelClass> {
            override fun onResponse(
                call: Call<CastProfileModelClass>,
                response: Response<CastProfileModelClass>
            ) {
                castProfileName.text = response.body()?.name
                castProfileJob.text = response.body()?.knownForDepartment
                val castGender = response.body()?.gender
                if (castGender == 1){castProfileGender.text = getString(R.string.castProfileGenderWoman)}
                else{castProfileGender.text = getString(R.string.castProfileGenderMan)}
                castProfileBirthDate.text = response.body()?.birthday
                castProfileBirthPlace.text = response.body()?.placeOfBirth
                castProfileBiograpy.text = response.body()?.biography
                val castImagePath = getString(R.string.POSTER_BASE_URL) + response.body()?.profilePath
                Picasso.get().load(castImagePath).into(castProfileImage)

            }
            override fun onFailure(call: Call<CastProfileModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })


    }

    fun castKonwnMoviesClickListener(cast:Cast){
        val intent = Intent(this, MovieInfoActivity::class.java)
        intent.putExtra("movieName", cast.MovieId)
        finish()
        startActivity(intent)
    }
}