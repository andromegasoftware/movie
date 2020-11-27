package com.andromega.moviepedia.tablayout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesApi
import com.andromega.moviepedia.action_movies.ActionMoviesMovieListModelClass
import com.andromega.moviepedia.kid_popular_movies.KidPopularMoviesAdapter
import com.andromega.moviepedia.kid_popular_movies.KidPopularMoviesApi
import com.andromega.moviepedia.kid_popular_movies.KidPopularMoviesListModelClass
import com.andromega.moviepedia.kid_popular_movies.KidPopularMoviesModelClass
import com.andromega.moviepedia.kid_top_rated_movies.KidTopRatedMoviesAdapter
import com.andromega.moviepedia.kid_top_rated_movies.KidTopRatedMoviesApi
import com.andromega.moviepedia.kid_top_rated_movies.KidTopRatedMoviesListModelClass
import com.andromega.moviepedia.kid_top_rated_movies.KidTopRatedMoviesModelClass
import com.andromega.moviepedia.movies.CircularMovieModelClass
import com.andromega.moviepedia.movies.CirculerImageAdapter
import com.andromega.moviepedia.romantic_movies.RomanticMoviesAdapter
import com.andromega.moviepedia.romantic_movies.RomanticMoviesModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesInterface
import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesMovieList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_kids.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.action_recyclerview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.NullPointerException
import kotlin.random.Random


class KidsFragment : Fragment() {

    var posterMovieId: Int = 0
    var random:Int = 0
    var posterMoviePath: String? = ""

    private lateinit var kidPopularMoviesAdapter: KidPopularMoviesAdapter
    lateinit var resultsKidPopularMovies: List<KidPopularMoviesModelClass>

    private lateinit var kidTopRatedMoviesAdapter: KidTopRatedMoviesAdapter
    lateinit var resultsKidTopRatedMovies: List<KidTopRatedMoviesModelClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kids, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        random = Random.nextInt(0,19)

        resultsKidPopularMovies = emptyList()
        kidPopularMoviesAdapter = KidPopularMoviesAdapter(resultsKidPopularMovies)
        { kidPopularMoviesModelClassItem: KidPopularMoviesModelClass ->
            kidPopularMoviesClickListener(kidPopularMoviesModelClassItem)
        }

        resultsKidTopRatedMovies = emptyList()
        kidTopRatedMoviesAdapter = KidTopRatedMoviesAdapter(resultsKidTopRatedMovies)
        { kidTopRatedMoviesModelClassItem: KidTopRatedMoviesModelClass ->
            kidTopRatedMoviesClickListener(kidTopRatedMoviesModelClassItem)
        }

        button_kid_movieInfo.setOnClickListener {
            val intent = Intent(context, MovieInfoActivity::class.java)
            intent.putExtra("movieName", posterMovieId)
            startActivity(intent)
        }

        //kid top rated videos recyclerview
        val kidTopRatedMoviesLayoutManager = LinearLayoutManager(context)
        kidTopRatedMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        kid_circular_recyclerview.layoutManager = kidTopRatedMoviesLayoutManager
        kid_circular_recyclerview.adapter = kidTopRatedMoviesAdapter

        //kid top rated movies circular recyclerview fetch data
        val retrofitKidTopRatedMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiKidTopRatedMovies = retrofitKidTopRatedMovies.create(KidTopRatedMoviesApi::class.java)
        apiKidTopRatedMovies.getKidTopRatedMovies().enqueue(object : Callback<KidTopRatedMoviesListModelClass>{
            override fun onResponse(
                call: Call<KidTopRatedMoviesListModelClass>,
                response: Response<KidTopRatedMoviesListModelClass>
            ) {
                resultsKidTopRatedMovies = response.body()?.results ?: resultsKidTopRatedMovies
                kidTopRatedMoviesAdapter.submitList(resultsKidTopRatedMovies)
                kidTopRatedMoviesAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<KidTopRatedMoviesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

        //kid popular movies recyclerview
        val kidPopularMoviesLayoutManager = GridLayoutManager(context, 2)
        kid_popular_recyclerview.layoutManager = kidPopularMoviesLayoutManager
        kid_popular_recyclerview.adapter = kidPopularMoviesAdapter

        //action movies recyclerview fetch data
        val retrofitKidPopularMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiKidPopularMovies = retrofitKidPopularMovies.create(KidPopularMoviesApi::class.java)
        apiKidPopularMovies.getKidPopularMovies().enqueue(object :
            Callback<KidPopularMoviesListModelClass> {
            override fun onResponse(
                call: Call<KidPopularMoviesListModelClass>,
                response: Response<KidPopularMoviesListModelClass>
            ) {
                resultsKidPopularMovies = response.body()?.results ?: resultsKidPopularMovies
                kidPopularMoviesAdapter.submitList(resultsKidPopularMovies)
                kidPopularMoviesAdapter.notifyDataSetChanged()

                //randomly puts posters in main poster
                posterMovieId = resultsKidPopularMovies[random].id
                posterMoviePath = resultsKidPopularMovies[random].backdropPath
                val moviePosterURL: String = resources.getString(R.string.POSTER_BASE_URL) + posterMoviePath
                Picasso.get().load(moviePosterURL).error(R.drawable.avenger).into(imageView_kid_poster)
            }

            override fun onFailure(call: Call<KidPopularMoviesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })


    }

    fun kidPopularMoviesClickListener(kidPopularMoviesModelClass: KidPopularMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", kidPopularMoviesModelClass.id)
        startActivity(intent)
    }

    fun kidTopRatedMoviesClickListener(kidTopRatedMoviesModelClass: KidTopRatedMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", kidTopRatedMoviesModelClass.id)
        startActivity(intent)
    }
}