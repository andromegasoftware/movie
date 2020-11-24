package com.andromega.moviepedia.single_movie_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.cast_profile.CastProfileActivity
import com.andromega.moviepedia.movie_reviews.CommentActivity
import com.andromega.moviepedia.R
import com.andromega.moviepedia.data.vo.Genre
import com.andromega.moviepedia.data.vo.MovieDetails
import com.andromega.moviepedia.movie_cast.MovieCastListApi
import com.andromega.moviepedia.movie_cast.MovieCastListModelClass
import com.andromega.moviepedia.movie_cast.MovieCastModelClass
import com.andromega.moviepedia.movie_cast.MovieCastRecyclerviewAdapter
import com.andromega.moviepedia.movie_trailer.MovieFragmentModelClass
import com.andromega.moviepedia.movie_trailer.MovieYouTubeFragmentApi
import com.andromega.moviepedia.movie_reviews.MovieReviewsApi
import com.andromega.moviepedia.movie_reviews.MovieReviewsModelClass
import com.andromega.moviepedia.movie_reviews.ResultReviewDetailModelClass
import com.andromega.moviepedia.similar_movies.ResultModelClass
import com.andromega.moviepedia.similar_movies.SimilarMoviesAdapter
import com.andromega.moviepedia.similar_movies.SimilarMoviesApi
import com.andromega.moviepedia.similar_movies.SimilarMoviesModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MovieInfoActivity : AppCompatActivity() {

    var movieYouTubeFragmentId = ""
    var movieId : String = ""
    lateinit var genre: List<Genre>

    var reviewsDetailsList:List<ResultReviewDetailModelClass> = ArrayList()

    private lateinit var movieCastRecyclerviewAdapter: MovieCastRecyclerviewAdapter
    lateinit var castNameList: List<MovieCastModelClass>

    private lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    lateinit var similarMoviesList: List<ResultModelClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)

        //this is for actionbar and image view using instead of title
        val actionBar = getSupportActionBar()
        actionBar?.setCustomView(R.layout.logo)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowCustomEnabled(true)


        castNameList = emptyList()
        genre = emptyList()
        similarMoviesList = emptyList()
        //reviewsDetailsList = emptyList()

        movieId = intent.getIntExtra("movieName", 1).toString()

        buttonPlayFragment.setOnClickListener {
            this.openYoutubeLink(movieYouTubeFragmentId)
        }


        movieCastRecyclerviewAdapter = MovieCastRecyclerviewAdapter(castNameList, {castModelClassItem: MovieCastModelClass -> castProfileClickListener(castModelClassItem)})
        similarMoviesAdapter = SimilarMoviesAdapter(similarMoviesList, { similarMoviesModelClassItem: ResultModelClass -> similarMoviesClickListener(similarMoviesModelClassItem)})

        //similar movies list recyclerview
        val similarMoviesListlayoutManager = LinearLayoutManager(this)
        similarMoviesListlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        similar_movies_recyclerview.layoutManager = similarMoviesListlayoutManager
        similar_movies_recyclerview.adapter = similarMoviesAdapter

        //similar movies list recyclerview fetch data
        val retrofitSimilarMoviesList = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiSimilarMoviesList = retrofitSimilarMoviesList.create(SimilarMoviesApi::class.java)
        apiSimilarMoviesList.getSimilarMovies(movieId = movieId.toString()).enqueue(object : Callback<SimilarMoviesModelClass>{
            override fun onResponse(
                call: Call<SimilarMoviesModelClass>,
                response: Response<SimilarMoviesModelClass>
            ) {
                similarMoviesList = response.body()?.results ?: similarMoviesList
                similarMoviesAdapter.submitList(similarMoviesList)
                similarMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SimilarMoviesModelClass>, t: Throwable) {
                t.message?.let { Log.e("mesaj", it) }
            }
        })

        //cast list recyclerview
        val castListlayoutManager = LinearLayoutManager(this)
        castListlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        circular_recyclerview_cast.layoutManager = castListlayoutManager
        circular_recyclerview_cast.adapter = movieCastRecyclerviewAdapter

        //cast list recyclerview fetch data
        val retrofitCastList = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCastList = retrofitCastList.create(MovieCastListApi::class.java)
        apiCastList.getMovieCasts(movieId = movieId.toString()).enqueue(object : Callback<MovieCastListModelClass>{
            override fun onResponse(
                call: Call<MovieCastListModelClass>,
                response: Response<MovieCastListModelClass>
            ) {
                castNameList = response.body()?.results ?: castNameList
                movieCastRecyclerviewAdapter.submitList(castNameList)
                movieCastRecyclerviewAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MovieCastListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })


        //getting movie info from rest api
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(MovieInfoApiInterface::class.java)

        api.fetchAllData(movieId = movieId.toString()).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {

                textViewMovieDetailsTitle.text = response.body()?.title
                textViewMovieDetailsUserScore.text = getString(R.string.movie_info_score) + response.body()?.voteAverage
                textViewMovieDetailsYear.text = getString(R.string.movie_info_date) + response.body()?.releaseDate
                textViewMovieDetailsOverView.text = response.body()?.overview
                val dataPath: String = response.body()?.posterPath.toString()
                val moviePosterURL: String = getString(R.string.POSTER_BASE_URL) + dataPath

                genre = response.body()?.genres!!
                textViewMovieDetailsCategory.text = getString(R.string.movie_info_genre) + genre[0].name


                //Log.e("genre: ", genre[0].name)

                Picasso.get().load(moviePosterURL).into(imageViewMoveiDetailsPosterImage)

            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Log.e("movie: ",t.message.toString())
            }

        })

        //getting data about movie fragment from rest api
        val retrofitMovieFragment = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youTubeApi = retrofitMovieFragment.create(MovieYouTubeFragmentApi::class.java)

        youTubeApi.getFragments(movieId = movieId.toString()).enqueue(object : Callback<MovieFragmentModelClass> {
            override fun onResponse(call: Call<MovieFragmentModelClass>, response: Response<MovieFragmentModelClass>) {
                if(!response.body()?.results?.isEmpty()!!) {
                    movieYouTubeFragmentId = response.body()?.results?.get(0)?.MovieYouTubeKey.toString()
                    Log.e("youtube key onresponse: ", movieYouTubeFragmentId)

                }
                else{Log.e("movie error else: ", response.body()?.results.toString())}

            }

            override fun onFailure(call: Call<MovieFragmentModelClass>, t: Throwable) {
                Log.e("movie error: ",t.message.toString())
            }
        })

        //getting movie reviews from rest api
        val retrofitMovieReviews = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val reviewsApi = retrofitMovieReviews.create(MovieReviewsApi::class.java)

        reviewsApi.getMovieRevies(movieId = movieId).enqueue(object : Callback<MovieReviewsModelClass> {
            override fun onResponse(call: Call<MovieReviewsModelClass>, response: Response<MovieReviewsModelClass>) {
                val reviewsCount = response.body()?.totalReviews.toString()
                if(reviewsCount == "0"){
                    buttonGotoComment.isClickable = false
                }
                buttonGotoComment.text = getString(R.string.read_reviews_about_this_movie) + " (" + reviewsCount + ")"

                reviewsDetailsList = response.body()?.results ?: reviewsDetailsList
            }

            override fun onFailure(call: Call<MovieReviewsModelClass>, t: Throwable) {
                Log.e("movie: ",t.message.toString())
            }

        })

        buttonGotoComment.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("movieRevies",  reviewsDetailsList as Serializable)

            startActivity(intent)
        }

    }

    fun castProfileClickListener(castModelClass: MovieCastModelClass){
        val intent = Intent(this, CastProfileActivity::class.java)
        intent.putExtra("castId", castModelClass.castId)
        startActivity(intent)
    }

    fun similarMoviesClickListener(resultModelClass: ResultModelClass){
        val intent = Intent(this, MovieInfoActivity::class.java)
        intent.putExtra("movieName", resultModelClass.movieId)
        startActivity(intent)
        finish()
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

}