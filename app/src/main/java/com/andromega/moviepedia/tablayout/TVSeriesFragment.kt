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
import com.andromega.moviepedia.`sci-fic_tv_series`.SciFicTvSeriesAdapter
import com.andromega.moviepedia.`sci-fic_tv_series`.SciFicTvSeriesApi
import com.andromega.moviepedia.`sci-fic_tv_series`.SciFicTvSeriesListModelClass
import com.andromega.moviepedia.`sci-fic_tv_series`.ScienceFictionTvSeriesModelClass
import com.andromega.moviepedia.action_tv_series.ActionTvSeriesAdapter
import com.andromega.moviepedia.action_tv_series.ActionTvSeriesApi
import com.andromega.moviepedia.action_tv_series.ActionTvSeriesListModelClass
import com.andromega.moviepedia.action_tv_series.ActionTvSeriesModelClass
import com.andromega.moviepedia.comedy_tv_series.ComedyTvSeriesAdapter
import com.andromega.moviepedia.comedy_tv_series.ComedyTvSeriesApi
import com.andromega.moviepedia.comedy_tv_series.ComedyTvSeriesListModelClass
import com.andromega.moviepedia.comedy_tv_series.ComedyTvSeriesModelClass
import com.andromega.moviepedia.crime_tv_series.CrimeTvSeriesAdapter
import com.andromega.moviepedia.crime_tv_series.CrimeTvSeriesApi
import com.andromega.moviepedia.crime_tv_series.CrimeTvSeriesListModelClass
import com.andromega.moviepedia.crime_tv_series.CrimeTvSeriesModelClass
import com.andromega.moviepedia.documentary_tv_series.DocumentaryTvSeriesAdapter
import com.andromega.moviepedia.documentary_tv_series.DocumentaryTvSeriesApi
import com.andromega.moviepedia.documentary_tv_series.DocumentaryTvSeriesListModelClass
import com.andromega.moviepedia.documentary_tv_series.DocumentaryTvSeriesModelClass
import com.andromega.moviepedia.drama_tv_series.DramaTvSeriesAdapter
import com.andromega.moviepedia.drama_tv_series.DramaTvSeriesApi
import com.andromega.moviepedia.drama_tv_series.DramaTvSeriesListModelClass
import com.andromega.moviepedia.drama_tv_series.DramaTvSeriesModelClass
import com.andromega.moviepedia.family_tv_series.FamilyTvSeriesAdapter
import com.andromega.moviepedia.family_tv_series.FamilyTvSeriesApi
import com.andromega.moviepedia.family_tv_series.FamilyTvSeriesListModelClass
import com.andromega.moviepedia.family_tv_series.FamilyTvSeriesModelClass
import com.andromega.moviepedia.popular_tv_series.PopularTvSeriesAdapter
import com.andromega.moviepedia.popular_tv_series.PopularTvSeriesApi
import com.andromega.moviepedia.popular_tv_series.PopularTvSeriesListModelClass
import com.andromega.moviepedia.popular_tv_series.PopularTvSeriesModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesAdapter
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesApi
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesListModelClass
import com.andromega.moviepedia.top_rated_tv_series.TopRatedTvSeriesModelClass
import com.andromega.moviepedia.tv_series_info.TvSeriesInfo
import com.andromega.moviepedia.war_tv_series.WarTvSeriesAdapter
import com.andromega.moviepedia.war_tv_series.WarTvSeriesApi
import com.andromega.moviepedia.war_tv_series.WarTvSeriesListModelClass
import com.andromega.moviepedia.war_tv_series.WarTvSeriesModelClass
import com.andromega.moviepedia.western_tv_series.WesternTvSeriesAdapter
import com.andromega.moviepedia.western_tv_series.WesternTvSeriesApi
import com.andromega.moviepedia.western_tv_series.WesternTvSeriesListModelClass
import com.andromega.moviepedia.western_tv_series.WesternTvSeriesModelClass
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

    private lateinit var popularTvSeriesAdapter: PopularTvSeriesAdapter
    lateinit var resultsPopularTvSeries: List<PopularTvSeriesModelClass>

    private lateinit var actionTvSeriesAdapter: ActionTvSeriesAdapter
    lateinit var resultsActionTvSeries: List<ActionTvSeriesModelClass>

    private lateinit var sciFicTvSeriesAdapter: SciFicTvSeriesAdapter
    lateinit var resultsSciFicTvSeries: List<ScienceFictionTvSeriesModelClass>

    private lateinit var familyTvSeriesAdapter: FamilyTvSeriesAdapter
    lateinit var resultsFamilyTvSeries: List<FamilyTvSeriesModelClass>

    private lateinit var comedyTvSeriesAdapter: ComedyTvSeriesAdapter
    lateinit var resultsComedyTvSeries: List<ComedyTvSeriesModelClass>

    private lateinit var warTvSeriesAdapter: WarTvSeriesAdapter
    lateinit var resultsWarTvSeries: List<WarTvSeriesModelClass>

    private lateinit var crimeTvSeriesAdapter: CrimeTvSeriesAdapter
    lateinit var resultsCrimeTvSeries: List<CrimeTvSeriesModelClass>

    private lateinit var westernTvSeriesAdapter: WesternTvSeriesAdapter
    lateinit var resultsWesternTvSeries: List<WesternTvSeriesModelClass>

    private lateinit var documentaryTvSeriesAdapter: DocumentaryTvSeriesAdapter
    lateinit var resultsDocumentaryTvSeries: List<DocumentaryTvSeriesModelClass>

    private lateinit var dramaTvSeriesAdapter: DramaTvSeriesAdapter
    lateinit var resultsDramaTvSeries: List<DramaTvSeriesModelClass>

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
        topRatedTvSeriesAdapter = TopRatedTvSeriesAdapter(resultsTopRatedTvSeries) { topRatedTvSeriesModelClassItem: TopRatedTvSeriesModelClass ->
            topRatedTvSeriesClickListener(
                topRatedTvSeriesModelClassItem
            )
        }

        resultsPopularTvSeries = emptyList()
        popularTvSeriesAdapter = PopularTvSeriesAdapter(resultsPopularTvSeries) { popularTvSeriesModelClassItem: PopularTvSeriesModelClass ->
            popularTvSeriesClickListener(
                popularTvSeriesModelClassItem
            )
        }

        resultsActionTvSeries = emptyList()
        actionTvSeriesAdapter = ActionTvSeriesAdapter(resultsActionTvSeries) { actionTvSeriesModelClassItem: ActionTvSeriesModelClass ->
            actionTvSeriesClickListener(
                actionTvSeriesModelClassItem
            )
        }

        resultsSciFicTvSeries = emptyList()
        sciFicTvSeriesAdapter = SciFicTvSeriesAdapter(resultsSciFicTvSeries) { sciFicTvSeriesModelClassItem: ScienceFictionTvSeriesModelClass ->
            sciFicTvSeriesClickListener(
                sciFicTvSeriesModelClassItem
            )
        }

        resultsFamilyTvSeries = emptyList()
        familyTvSeriesAdapter = FamilyTvSeriesAdapter(resultsFamilyTvSeries) { familyTvSeriesModelClassItem: FamilyTvSeriesModelClass ->
            familyTvSeriesClickListener(
                familyTvSeriesModelClassItem
            )
        }

        resultsComedyTvSeries = emptyList()
        comedyTvSeriesAdapter = ComedyTvSeriesAdapter(resultsComedyTvSeries) { comedyTvSeriesModelClassItem: ComedyTvSeriesModelClass ->
            comedyTvSeriesClickListener(
                comedyTvSeriesModelClassItem
            )
        }

        resultsWarTvSeries = emptyList()
        warTvSeriesAdapter = WarTvSeriesAdapter(resultsWarTvSeries) { warTvSeriesModelClassItem: WarTvSeriesModelClass ->
            warTvSeriesClickListener(
                warTvSeriesModelClassItem
            )
        }

        resultsCrimeTvSeries = emptyList()
        crimeTvSeriesAdapter = CrimeTvSeriesAdapter(resultsCrimeTvSeries) { crimeTvSeriesModelClassItem: CrimeTvSeriesModelClass ->
            crimeTvSeriesClickListener(
                crimeTvSeriesModelClassItem
            )
        }

        resultsWesternTvSeries = emptyList()
        westernTvSeriesAdapter = WesternTvSeriesAdapter(resultsWesternTvSeries) { westernTvSeriesModelClassItem: WesternTvSeriesModelClass ->
            westernTvSeriesClickListener(
                westernTvSeriesModelClassItem
            )
        }

        resultsDocumentaryTvSeries = emptyList()
        documentaryTvSeriesAdapter = DocumentaryTvSeriesAdapter(resultsDocumentaryTvSeries) { documentaryTvSeriesModelClassItem: DocumentaryTvSeriesModelClass ->
            documentaryTvSeriesClickListener(
                documentaryTvSeriesModelClassItem
            )
        }

        resultsDramaTvSeries = emptyList()
        dramaTvSeriesAdapter = DramaTvSeriesAdapter(resultsDramaTvSeries) { dramaTvSeriesModelClassItem: DramaTvSeriesModelClass ->
            dramaTvSeriesClickListener(
                dramaTvSeriesModelClassItem
            )
        }

        //drama tv series recyclerview
        val dramaTvLayoutManager = LinearLayoutManager(context)
        dramaTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        drama_recyclerview_tv.layoutManager = dramaTvLayoutManager
        drama_recyclerview_tv.adapter = dramaTvSeriesAdapter

        //drama tv series recyclerview fetch data
        val retrofitDramaTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiDramaTvSeries = retrofitDramaTvSeries.create(DramaTvSeriesApi::class.java)
        apiDramaTvSeries.getDramaTvSeries().enqueue(object : Callback<DramaTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<DramaTvSeriesListModelClass>,
                response: Response<DramaTvSeriesListModelClass>
            ) {
                resultsDramaTvSeries= response.body()?.results ?: resultsDramaTvSeries
                dramaTvSeriesAdapter.submitList(resultsDramaTvSeries)
                dramaTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DramaTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //documentary tv series recyclerview
        val documentaryTvLayoutManager = LinearLayoutManager(context)
        documentaryTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        documentary_recyclerview_tv.layoutManager = documentaryTvLayoutManager
        documentary_recyclerview_tv.adapter = documentaryTvSeriesAdapter

        //documentary tv series recyclerview fetch data
        val retrofitDocumentaryTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiDocumentaryTvSeries = retrofitDocumentaryTvSeries.create(DocumentaryTvSeriesApi::class.java)
        apiDocumentaryTvSeries.getDocumentaryTvSeries().enqueue(object : Callback<DocumentaryTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<DocumentaryTvSeriesListModelClass>,
                response: Response<DocumentaryTvSeriesListModelClass>
            ) {
                resultsDocumentaryTvSeries= response.body()?.results ?: resultsDocumentaryTvSeries
                documentaryTvSeriesAdapter.submitList(resultsDocumentaryTvSeries)
                documentaryTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DocumentaryTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //western tv series recyclerview
        val westernTvLayoutManager = LinearLayoutManager(context)
        westernTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        western_recyclerview_tv.layoutManager = westernTvLayoutManager
        western_recyclerview_tv.adapter = westernTvSeriesAdapter

        //western tv series recyclerview fetch data
        val retrofitWesternTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiWesternTvSeries = retrofitWesternTvSeries.create(WesternTvSeriesApi::class.java)
        apiWesternTvSeries.getWesternTvSeries().enqueue(object : Callback<WesternTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<WesternTvSeriesListModelClass>,
                response: Response<WesternTvSeriesListModelClass>
            ) {
                resultsWesternTvSeries= response.body()?.results ?: resultsWesternTvSeries
                westernTvSeriesAdapter.submitList(resultsWesternTvSeries)
                westernTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<WesternTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //crime tv series recyclerview
        val crimeTvLayoutManager = LinearLayoutManager(context)
        crimeTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        crime_recyclerview_tv.layoutManager = crimeTvLayoutManager
        crime_recyclerview_tv.adapter = crimeTvSeriesAdapter

        //crime tv series recyclerview fetch data
        val retrofitCrimeTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCrimeTvSeries = retrofitCrimeTvSeries.create(CrimeTvSeriesApi::class.java)
        apiCrimeTvSeries.getCrimeTvSeries().enqueue(object : Callback<CrimeTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<CrimeTvSeriesListModelClass>,
                response: Response<CrimeTvSeriesListModelClass>
            ) {
                resultsCrimeTvSeries= response.body()?.results ?: resultsCrimeTvSeries
                crimeTvSeriesAdapter.submitList(resultsCrimeTvSeries)
                crimeTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CrimeTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //war tv series recyclerview
        val warTvLayoutManager = LinearLayoutManager(context)
        warTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        war_recyclerview_tv.layoutManager = warTvLayoutManager
        war_recyclerview_tv.adapter = warTvSeriesAdapter

        //war tv series recyclerview fetch data
        val retrofitWarTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiWarTvSeries = retrofitWarTvSeries.create(WarTvSeriesApi::class.java)
        apiWarTvSeries.getWarTvSeries().enqueue(object : Callback<WarTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<WarTvSeriesListModelClass>,
                response: Response<WarTvSeriesListModelClass>
            ) {
                resultsWarTvSeries= response.body()?.results ?: resultsWarTvSeries
                warTvSeriesAdapter.submitList(resultsWarTvSeries)
                warTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<WarTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //comedy tv series recyclerview
        val comedyTvLayoutManager = LinearLayoutManager(context)
        comedyTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        comedy_recyclerview_tv.layoutManager = comedyTvLayoutManager
        comedy_recyclerview_tv.adapter = comedyTvSeriesAdapter

        //comedy tv series recyclerview fetch data
        val retrofitComedyTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiComedyTvSeries = retrofitComedyTvSeries.create(ComedyTvSeriesApi::class.java)
        apiComedyTvSeries.getComedyTvSeries().enqueue(object : Callback<ComedyTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<ComedyTvSeriesListModelClass>,
                response: Response<ComedyTvSeriesListModelClass>
            ) {
                resultsComedyTvSeries= response.body()?.results ?: resultsComedyTvSeries
                comedyTvSeriesAdapter.submitList(resultsComedyTvSeries)
                comedyTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ComedyTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //family tv series recyclerview
        val familyTvLayoutManager = LinearLayoutManager(context)
        familyTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        family_recyclerview_tv.layoutManager = familyTvLayoutManager
        family_recyclerview_tv.adapter = familyTvSeriesAdapter

        //family tv series recyclerview fetch data
        val retrofitFamilyTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiFamilyTvSeries = retrofitFamilyTvSeries.create(FamilyTvSeriesApi::class.java)
        apiFamilyTvSeries.getFamilyTvSeries().enqueue(object : Callback<FamilyTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<FamilyTvSeriesListModelClass>,
                response: Response<FamilyTvSeriesListModelClass>
            ) {
                resultsFamilyTvSeries= response.body()?.results ?: resultsFamilyTvSeries
                familyTvSeriesAdapter.submitList(resultsFamilyTvSeries)
                familyTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<FamilyTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //sci fic series recyclerview
        val sciFicTvLayoutManager = LinearLayoutManager(context)
        sciFicTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        science_fiction_recyclerview_tv.layoutManager = sciFicTvLayoutManager
        science_fiction_recyclerview_tv.adapter = sciFicTvSeriesAdapter

        //sci fic series recyclerview fetch data
        val retrofitSciFicTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiSciFicTvSeries = retrofitSciFicTvSeries.create(SciFicTvSeriesApi::class.java)
        apiSciFicTvSeries.getSciFicTvSeries().enqueue(object : Callback<SciFicTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<SciFicTvSeriesListModelClass>,
                response: Response<SciFicTvSeriesListModelClass>
            ) {
                resultsSciFicTvSeries= response.body()?.results ?: resultsSciFicTvSeries
                sciFicTvSeriesAdapter.submitList(resultsSciFicTvSeries)
                sciFicTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SciFicTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //action tv series recyclerview
        val actionTvLayoutManager = LinearLayoutManager(context)
        actionTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        action_recyclerview_tv.layoutManager = actionTvLayoutManager
        action_recyclerview_tv.adapter = actionTvSeriesAdapter

        //action tv series recyclerview fetch data
        val retrofitActionTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiActionTvSeries = retrofitActionTvSeries.create(ActionTvSeriesApi::class.java)
        apiActionTvSeries.getActionTvSeries().enqueue(object : Callback<ActionTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<ActionTvSeriesListModelClass>,
                response: Response<ActionTvSeriesListModelClass>
            ) {
                resultsActionTvSeries= response.body()?.results ?: resultsActionTvSeries
                actionTvSeriesAdapter.submitList(resultsActionTvSeries)
                actionTvSeriesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ActionTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //popular tv series recyclerview
        val popularTvLayoutManager = LinearLayoutManager(context)
        popularTvLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        newcoming_recyclerview_tv.layoutManager = popularTvLayoutManager
        newcoming_recyclerview_tv.adapter = popularTvSeriesAdapter

        //popular tv series recyclerview fetch data
        val retrofitPopularTvSeries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiPopularTvSeries = retrofitPopularTvSeries.create(PopularTvSeriesApi::class.java)
        apiPopularTvSeries.getPopularTvSeries().enqueue(object : Callback<PopularTvSeriesListModelClass> {
            override fun onResponse(
                call: Call<PopularTvSeriesListModelClass>,
                response: Response<PopularTvSeriesListModelClass>
            ) {
                resultsPopularTvSeries = response.body()?.results ?: resultsPopularTvSeries
                popularTvSeriesAdapter.submitList(resultsPopularTvSeries)
                popularTvSeriesAdapter.notifyDataSetChanged()

                //randomly puts posters in main poster
                posterMovieId = resultsPopularTvSeries[random].id
                posterMoviePath = resultsPopularTvSeries[random].picturePoster
                val moviePosterURL: String = getString(R.string.POSTER_BASE_URL) + posterMoviePath
                Picasso.get().load(moviePosterURL).error(R.drawable.avenger).into(imageView_tv_poster)
            }

            override fun onFailure(call: Call<PopularTvSeriesListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

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

    fun popularTvSeriesClickListener(popularTvSeriesModelClass: PopularTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", popularTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun actionTvSeriesClickListener(actionTvSeriesModelClass: ActionTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", actionTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun sciFicTvSeriesClickListener(scienceFictionTvSeriesModelClass: ScienceFictionTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", scienceFictionTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun familyTvSeriesClickListener(familyTvSeriesModelClass: FamilyTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", familyTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun comedyTvSeriesClickListener(comedyTvSeriesModelClass: ComedyTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", comedyTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun warTvSeriesClickListener(warTvSeriesModelClass: WarTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", warTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun crimeTvSeriesClickListener(crimeTvSeriesModelClass: CrimeTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", crimeTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun westernTvSeriesClickListener(westernTvSeriesModelClass: WesternTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", westernTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun documentaryTvSeriesClickListener(documentaryTvSeriesModelClass: DocumentaryTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", documentaryTvSeriesModelClass.id)
        startActivity(intent)
    }

    fun dramaTvSeriesClickListener(dramaTvSeriesModelClass: DramaTvSeriesModelClass){
        val intent = Intent(context, TvSeriesInfo::class.java)
        intent.putExtra("tvSeriesId", dramaTvSeriesModelClass.id)
        startActivity(intent)
    }

}