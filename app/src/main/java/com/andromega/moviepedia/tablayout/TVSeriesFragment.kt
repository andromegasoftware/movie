package com.andromega.moviepedia.tablayout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesAdapter
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesApi
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesListModelClass
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesModelClass
import com.andromega.moviepedia.tv_series_info.TvSeriesInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_t_v_series.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class TVSeriesFragment : Fragment() {

    var posterMovieId: Int = 0
    var random:Int = 0
    var posterMoviePath: String = ""

    private lateinit var topRatedTvSeriesAdapter: TopRatedTvSeriesAdapter
    lateinit var resultsTopRatedTvSeries: List<TopRatedTvSeriesModelClass>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonGotoTvInfo.setOnClickListener {
            val intent = Intent(context, TvSeriesInfo::class.java)
            intent.putExtra("tvSeriesId", posterMovieId)
            startActivity(intent)
        }

        random = Random.nextInt(0,19)
        resultsTopRatedTvSeries = emptyList()
        topRatedTvSeriesAdapter = TopRatedTvSeriesAdapter(resultsTopRatedTvSeries, {topRatedTvSeriesModelClassItem: TopRatedTvSeriesModelClass -> topRatedTvSeriesClickListener(topRatedTvSeriesModelClassItem)})

        //top rated tv series recyclerview
        val topRatedTvLayoutManager = LinearLayoutManager(context)
        topRatedTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        circular_recyclerview_tv.layoutManager = topRatedTvLayoutManager
        circular_recyclerview_tv.adapter = topRatedTvSeriesAdapter

        //top rated movies circular recyclerview fetch data
        val retrofitTopratedTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiTopRatedTvSeries = retrofitTopratedTvSeries.create(TopRatedTvSeriesApi::class.java)
        apiTopRatedTvSeries.getTopRatedTvSeries().enqueue(object : Callback<TopRatedTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<TopRatedTvSeriesListModelClass>,
                response: Response<TopRatedTvSeriesListModelClass>
            ) {
                resultsTopRatedTvSeries = response.body()?.results ?: resultsTopRatedTvSeries
                topRatedTvSeriesAdapter.submitList(resultsTopRatedTvSeries)
                topRatedTvSeriesAdapter.notifyDataSetChanged()

                //randomly puts posters in main poster
                posterMovieId = resultsTopRatedTvSeries[random].id
                posterMoviePath = resultsTopRatedTvSeries[random].picturePoster
                val moviePosterURL: String = getString(R.string.POSTER_BASE_URL) + posterMoviePath
                Picasso.get().load(moviePosterURL).error(R.drawable.avenger).into(imageView_tv_poster)
            }

            override fun onFailure(call: Call<TopRatedTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

    }

    fun topRatedTvSeriesClickListener(topRatedTvSeriesModelClass: TopRatedTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", topRatedTvSeriesModelClass.id)
        startActivity(intent)
    }

}