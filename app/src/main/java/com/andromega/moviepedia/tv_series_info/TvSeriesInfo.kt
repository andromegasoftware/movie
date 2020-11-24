package com.andromega.moviepedia.tv_series_info

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.movie_reviews.CommentActivity
import com.andromega.moviepedia.R
import com.andromega.moviepedia.cast_profile.CastProfileActivity
import com.andromega.moviepedia.similar_tv_series.ResultTvModelClass
import com.andromega.moviepedia.similar_tv_series.SimilarTvSeriesAdapter
import com.andromega.moviepedia.similar_tv_series.SimilarTvSeriesApi
import com.andromega.moviepedia.similar_tv_series.SimilarTvSeriesModelClass
import com.andromega.moviepedia.tv_series_cast_list.TvCastModelClass
import com.andromega.moviepedia.tv_series_cast_list.TvSeriesCastListApi
import com.andromega.moviepedia.tv_series_cast_list.TvSeriesCastListModelClass
import com.andromega.moviepedia.tv_series_cast_list.TvSeriesCastRecyclerviewAdapter
import com.andromega.moviepedia.tv_series_reviews.TvReviewsActivity
import com.andromega.moviepedia.tv_series_reviews.TvReviewsModelClass
import com.andromega.moviepedia.tv_series_reviews.TvSeriesResultReviewModelClass
import com.andromega.moviepedia.tv_series_reviews.TvSeriesReviewsApi
import com.andromega.moviepedia.tv_series_trailer.TvSeriesTrailerModelClass
import com.andromega.moviepedia.tv_series_trailer.TvYouTubeTrailerApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_series_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class TvSeriesInfo : AppCompatActivity() {

    var tvSeriesYouTubeFragmentId = ""
    var tvSeriesId : String = ""
    lateinit var genreTv: List<GenreTv>

    var reviewsDetailsList:List<TvSeriesResultReviewModelClass> = ArrayList()

    private lateinit var tvSeriesCastRecyclerviewAdapter: TvSeriesCastRecyclerviewAdapter
    lateinit var castNameList: List<TvCastModelClass>

    private lateinit var similarTvSeriesAdapter: SimilarTvSeriesAdapter
    lateinit var similarTvSeriesList: List<ResultTvModelClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_series_info)

        //this is for actionbar and image view using instead of title
        val actionBar = supportActionBar
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)

        tvSeriesId = intent.getIntExtra("tvSeriesId", 1).toString()
        genreTv = emptyList()
        castNameList = emptyList()
        similarTvSeriesList = emptyList()

        tvSeriesCastRecyclerviewAdapter = TvSeriesCastRecyclerviewAdapter(castNameList) { castModelClassItem: TvCastModelClass ->
            castProfileClickListener(
                castModelClassItem
            )
        }
        similarTvSeriesAdapter = SimilarTvSeriesAdapter(similarTvSeriesList) { similarTvSeriesModelClassItem: ResultTvModelClass ->
            similarTvSeriesClickListener(
                similarTvSeriesModelClassItem
            )
        }

        //similar tv series list recyclerview
        val similarTvSeriesListlayoutManager = LinearLayoutManager(this)
        similarTvSeriesListlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        similar_tv_series_recyclerview.layoutManager = similarTvSeriesListlayoutManager
        similar_tv_series_recyclerview.adapter = similarTvSeriesAdapter

        //similar movies list recyclerview fetch data
        val retrofitSimilarTvSeriesList = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiSimilarTvSeriesList = retrofitSimilarTvSeriesList.create(SimilarTvSeriesApi::class.java)
        apiSimilarTvSeriesList.getSimilarTvSeries(tvSeriesId = tvSeriesId).enqueue(object : Callback<SimilarTvSeriesModelClass>{
            override fun onResponse(
                call: Call<SimilarTvSeriesModelClass>,
                response: Response<SimilarTvSeriesModelClass>
            ) {
                similarTvSeriesList = response.body()?.results ?: similarTvSeriesList
                similarTvSeriesAdapter.submitList(similarTvSeriesList)
                similarTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SimilarTvSeriesModelClass>, t: Throwable) {
                t.message?.let { Log.e("mesaj", it) }
            }
        })


        //cast list recyclerview
        val castListlayoutManager = LinearLayoutManager(this)
        castListlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        circular_recyclerview_tv_cast.layoutManager = castListlayoutManager
        circular_recyclerview_tv_cast.adapter = tvSeriesCastRecyclerviewAdapter

        //cast list recyclerview fetch data
        val retrofitCastList = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCastList = retrofitCastList.create(TvSeriesCastListApi::class.java)
        apiCastList.getTvSeriesCasts(tvSeriesId = tvSeriesId).enqueue(object : Callback<TvSeriesCastListModelClass>{
            override fun onResponse(
                call: Call<TvSeriesCastListModelClass>,
                response: Response<TvSeriesCastListModelClass>
            ) {
                castNameList = response.body()?.cast?: castNameList
                tvSeriesCastRecyclerviewAdapter.submitList(castNameList)
                tvSeriesCastRecyclerviewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TvSeriesCastListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //getting tv series info from rest api
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(TvSeriesInfoApi::class.java)
        api.fetchAllTvData(tvSeriesId = tvSeriesId).enqueue(object : Callback<TvSeriesInfoModelClass> {
            override fun onResponse(call: Call<TvSeriesInfoModelClass>, response: Response<TvSeriesInfoModelClass>) {

                textViewTvDetailsTitle.text = response.body()?.name.toString()
                textViewTvDetailsUserScore.text = getString(R.string.movie_info_score) + response.body()?.voteAverage.toString()
                textViewTvDetailsYear.text = getString(R.string.movie_info_date) + response.body()?.firstAirDate.toString()
                textViewTvDetailsOverView.text = response.body()?.overview.toString()
                val dataPath: String = response.body()?.backdropPath.toString()
                val moviePosterURL: String = getString(R.string.POSTER_BASE_URL) + dataPath

                genreTv = response.body()?.genres!!
                textViewTvDetailsCategory.text = getString(R.string.movie_info_genre) + genreTv[0].name.toString()
                //Log.e("genre: ", genre[0].name)

                Picasso.get().load(moviePosterURL).into(imageViewTvDetailsPosterImage)
            }
            override fun onFailure(call: Call<TvSeriesInfoModelClass>, t: Throwable) {
                Log.e("movie: ",t.message.toString())
            }
        })

        //getting data about tv series trailer from rest api
        val retrofitTvSeriesTrailer = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youTubeApi = retrofitTvSeriesTrailer.create(TvYouTubeTrailerApi::class.java)

        youTubeApi.getTvTrailer(tvSeriesId = tvSeriesId.toString()).enqueue(object : Callback<TvSeriesTrailerModelClass> {
            override fun onResponse(call: Call<TvSeriesTrailerModelClass>, response: Response<TvSeriesTrailerModelClass>) {
                if(!response.body()?.results?.isEmpty()!!) {
                    tvSeriesYouTubeFragmentId = response.body()?.results?.get(0)?.tvSeriesYouTubeKey.toString()
                   // Log.e("youtube key onresponse: ", tvSeriesYouTubeFragmentId)

                }
                else{Log.e("movie error else: ", response.body()?.results.toString())}

            }

            override fun onFailure(call: Call<TvSeriesTrailerModelClass>, t: Throwable) {
                Log.e("movie error: ",t.message.toString())
            }
        })

        buttonPlayTvFragment.setOnClickListener {
            openYoutubeLink(tvSeriesYouTubeFragmentId)
        }

        //getting movie reviews from rest api
        val retrofitTvReviews = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val reviewsApi = retrofitTvReviews.create(TvSeriesReviewsApi::class.java)

        reviewsApi.getTvSeriesRevies(tvSeriesId = tvSeriesId).enqueue(object : Callback<TvReviewsModelClass> {
            override fun onResponse(call: Call<TvReviewsModelClass>, response: Response<TvReviewsModelClass>) {
                val reviewsCount = response.body()?.totalReviews.toString()
                if(reviewsCount == "0"){
                    buttonGotoTvComment.isClickable = false
                }
                buttonGotoTvComment.text = getString(R.string.read_reviews_about_this_movie) + " (" + reviewsCount + ")"

                reviewsDetailsList = response.body()?.results ?: reviewsDetailsList

            }
            override fun onFailure(call: Call<TvReviewsModelClass>, t: Throwable) {
                Log.e("movie: ",t.message.toString())
            }
        })

        buttonGotoTvComment.setOnClickListener {
            val intent = Intent(this, TvReviewsActivity::class.java)
            intent.putExtra("tvRevies",  reviewsDetailsList as Serializable)
            Log.e("revies: ", reviewsDetailsList[0].author)
            Log.e("revies: ", reviewsDetailsList[0].content)
            startActivity(intent)
        }


    }

    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }

    fun castProfileClickListener(tvCastModelClass: TvCastModelClass){
        val intent = Intent(this, CastProfileActivity::class.java)
        intent.putExtra("castId", tvCastModelClass.castId)
        startActivity(intent)
    }

    fun similarTvSeriesClickListener(resultTvModelClass: ResultTvModelClass){
        val intent = Intent(this, TvSeriesInfo::class.java)
        intent.putExtra("movieName", resultTvModelClass.tvSeriesId)
        startActivity(intent)
        finish()
    }
}