package com.andromega.moviepedia.tablayout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.single_movie_details.MovieInfoActivity
import com.andromega.moviepedia.R
import com.andromega.moviepedia.action_movies.ActionMoviesAdaptor
import com.andromega.moviepedia.action_movies.ActionMoviesApi
import com.andromega.moviepedia.action_movies.ActionMoviesModelClass
import com.andromega.moviepedia.action_movies.ActionMoviesMovieListModelClass
import com.andromega.moviepedia.comedy.ComedyMoviesAdapter
import com.andromega.moviepedia.comedy.ComedyMoviesApi
import com.andromega.moviepedia.comedy.ComedyMoviesModelClass
import com.andromega.moviepedia.comedy.ComedyMoviesMovieListModelClass
import com.andromega.moviepedia.crime_movies.CrimeMoviesAdapter
import com.andromega.moviepedia.crime_movies.CrimeMoviesApi
import com.andromega.moviepedia.crime_movies.CrimeMoviesModelClass
import com.andromega.moviepedia.crime_movies.CrimeMoviesMovieListModelClass
import com.andromega.moviepedia.data.vo.MovieDetails
import com.andromega.moviepedia.documentaries.DocumentariesAdapter
import com.andromega.moviepedia.documentaries.DocumentariesApi
import com.andromega.moviepedia.documentaries.DocumentariesModelClass
import com.andromega.moviepedia.documentaries.DocumentariesMovieListModelClass
import com.andromega.moviepedia.family_movies.FamilyMoviesAdapter
import com.andromega.moviepedia.family_movies.FamilyMoviesApi
import com.andromega.moviepedia.family_movies.FamilyMoviesModelClass
import com.andromega.moviepedia.family_movies.FamilyMoviesMovieListModelClass
import com.andromega.moviepedia.history_movies.HistoryMoviesAdapter
import com.andromega.moviepedia.history_movies.HistoryMoviesApi
import com.andromega.moviepedia.history_movies.HistoryMoviesModelClass
import com.andromega.moviepedia.history_movies.HistoryMoviesMovieListModelClass
import com.andromega.moviepedia.movies.*
import com.andromega.moviepedia.romantic_movies.RomanticMoviesAdapter
import com.andromega.moviepedia.romantic_movies.RomanticMoviesApi
import com.andromega.moviepedia.romantic_movies.RomanticMoviesModelClass
import com.andromega.moviepedia.romantic_movies.RomanticMoviesMovieListModelClass
import com.andromega.moviepedia.science_fiction_movies.ScienFictionMoviesApi
import com.andromega.moviepedia.science_fiction_movies.ScienceFictionMoviesAdaptor
import com.andromega.moviepedia.science_fiction_movies.ScienceFictionMoviesModelClass
import com.andromega.moviepedia.science_fiction_movies.ScienceFictionMoviesMovieListModelClass
import com.andromega.moviepedia.single_movie_details.MovieInfoApiInterface
import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesInterface
import com.andromega.moviepedia.top_rated_movies.TopRatedMoviesMovieList
import com.andromega.moviepedia.war_movies.WarMoviesAdapter
import com.andromega.moviepedia.war_movies.WarMoviesApi
import com.andromega.moviepedia.war_movies.WarMoviesModelClass
import com.andromega.moviepedia.war_movies.WarMoviesMovieListModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_info.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.circular_recyclerview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MoviesFragment : Fragment() {

    private lateinit var circulerImageAdapter: CirculerImageAdapter
    private lateinit var newComingMoviesAdapter: NewComingMoviesAdapter
    lateinit var mnewComingMoviesApi: NewComingMoviesApi
    lateinit var results: List<NewComingMoviesModelClassNew>
    lateinit var resultsTopRatedMovies: List<CircularMovieModelClass>

    private lateinit var actionMoviesAdapter:ActionMoviesAdaptor
    lateinit var resultsActionMovies: List<ActionMoviesModelClass>

    private lateinit var scienceFictionMoviesAdaptor: ScienceFictionMoviesAdaptor
    lateinit var resultsScienceFictionMovies: List<ScienceFictionMoviesModelClass>

    private lateinit var familyMoviesAdapter: FamilyMoviesAdapter
    lateinit var resultsFamilyMovies: List<FamilyMoviesModelClass>

    private lateinit var comedyMoviesAdapter: ComedyMoviesAdapter
    lateinit var resultsComedyMovies: List<ComedyMoviesModelClass>

    private lateinit var warMoviesAdapter: WarMoviesAdapter
    lateinit var resultsWarMovies: List<WarMoviesModelClass>

    private lateinit var crimeMoviesAdapter: CrimeMoviesAdapter
    lateinit var resultsCrimeMovies: List<CrimeMoviesModelClass>

    private lateinit var historyMoviesAdapter: HistoryMoviesAdapter
    lateinit var resultsHistoryMovies: List<HistoryMoviesModelClass>

    private lateinit var documentariesAdapter: DocumentariesAdapter
    lateinit var resultsDocumentaries: List<DocumentariesModelClass>

    private lateinit var romanticMoviesAdapter: RomanticMoviesAdapter
    lateinit var resultsRomanticMovies: List<RomanticMoviesModelClass>

    var posterMovieId: Int = 0
    var random:Int = 0
    var posterMoviePath: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        results = emptyList()
        resultsTopRatedMovies = emptyList()
        resultsActionMovies = emptyList()
        resultsScienceFictionMovies = emptyList()
        resultsFamilyMovies = emptyList()
        resultsComedyMovies = emptyList()
        resultsWarMovies = emptyList()
        resultsCrimeMovies = emptyList()
        resultsHistoryMovies = emptyList()
        resultsDocumentaries = emptyList()
        resultsRomanticMovies = emptyList()
        random = Random.nextInt(0,19)

        circulerImageAdapter = CirculerImageAdapter(resultsTopRatedMovies, {circularMoviesModelClassItem: CircularMovieModelClass -> circularMoviesClickListener(circularMoviesModelClassItem)})

        newComingMoviesAdapter = NewComingMoviesAdapter(results, {newComingMoviesModelClassItem: NewComingMoviesModelClassNew -> newComingMoviesClickListener(newComingMoviesModelClassItem) })

        actionMoviesAdapter = ActionMoviesAdaptor(resultsActionMovies,{actionMoviesModelClassItem: ActionMoviesModelClass -> actionMoviesClickListener(actionMoviesModelClassItem)})

        scienceFictionMoviesAdaptor = ScienceFictionMoviesAdaptor(resultsScienceFictionMovies,{scienceMoviesModelClassItem: ScienceFictionMoviesModelClass -> scienceFictionMoviesClickListener(scienceMoviesModelClassItem)})

        familyMoviesAdapter = FamilyMoviesAdapter(resultsFamilyMovies, {familyMoviesModelClassItem: FamilyMoviesModelClass -> familyMoviesClickListener(familyMoviesModelClassItem)})

        comedyMoviesAdapter = ComedyMoviesAdapter(resultsComedyMovies, {comedyMoviesModelClassItem: ComedyMoviesModelClass -> comedyMoviesClickListener(comedyMoviesModelClassItem)})

        warMoviesAdapter = WarMoviesAdapter(resultsWarMovies, {warMoviesModelClassItem: WarMoviesModelClass -> warMoviesClickListener(warMoviesModelClassItem)})

        crimeMoviesAdapter = CrimeMoviesAdapter(resultsCrimeMovies, {crimeMoviesModelClassItem: CrimeMoviesModelClass -> crimeMoviesClickListener(crimeMoviesModelClassItem)})

        historyMoviesAdapter = HistoryMoviesAdapter(resultsHistoryMovies, {historyMoviesModelClassItem: HistoryMoviesModelClass -> historyMoviesClickListener(historyMoviesModelClassItem)})

        documentariesAdapter = DocumentariesAdapter(resultsDocumentaries, {documentariesModelClassItem: DocumentariesModelClass -> documentariesClickListener(documentariesModelClassItem)})

        romanticMoviesAdapter = RomanticMoviesAdapter(resultsRomanticMovies, {romanticMoviesModelClassItem: RomanticMoviesModelClass -> romanticMoviesClickListener(romanticMoviesModelClassItem)})

        mnewComingMoviesApi = Common.newComingMoviesApi



        buttonGotoMovieInfo.setOnClickListener {
            val intent = Intent(context, MovieInfoActivity::class.java)
            intent.putExtra("movieName", posterMovieId)
            startActivity(intent)
        }

        //special videos recyclerview
        val mlayoutManager = LinearLayoutManager(context)
        mlayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        circular_recyclerview.layoutManager = mlayoutManager
        circular_recyclerview.adapter = circulerImageAdapter

        //newcoming movies recyclerview
        val mNewComingMoviesLayoutManager = LinearLayoutManager(context)
        mNewComingMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        newcoming_recyclerview.layoutManager = mNewComingMoviesLayoutManager
        newcoming_recyclerview.adapter = newComingMoviesAdapter

        //action movies recyclerview
        val actionMoviesLayoutManager = LinearLayoutManager(context)
        actionMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        action_recyclerview.layoutManager = actionMoviesLayoutManager
        action_recyclerview.adapter = actionMoviesAdapter

        //science fiction movies recyclerview
        val scienceFictionMoviesLayoutManager = LinearLayoutManager(context)
        scienceFictionMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        science_fiction_recyclerview.layoutManager = scienceFictionMoviesLayoutManager
        science_fiction_recyclerview.adapter = scienceFictionMoviesAdaptor

        //family movies recyclerview
        val familyMoviesLayoutManager = LinearLayoutManager(context)
        familyMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        family_recyclerview.layoutManager = familyMoviesLayoutManager
        family_recyclerview.adapter = familyMoviesAdapter

        //comedy movies recyclerview
        val comedyMoviesLayoutManager = LinearLayoutManager(context)
        comedyMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        comedy_recyclerview.layoutManager = comedyMoviesLayoutManager
        comedy_recyclerview.adapter = comedyMoviesAdapter

        //war movies recyclerview
        val warMoviesLayoutManager = LinearLayoutManager(context)
        warMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        fantastic_recyclerview.layoutManager = warMoviesLayoutManager
        fantastic_recyclerview.adapter = warMoviesAdapter

        //crime movies recyclerview
        val crimeMoviesLayoutManager = LinearLayoutManager(context)
        crimeMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        crime_recyclerview.layoutManager = crimeMoviesLayoutManager
        crime_recyclerview.adapter = crimeMoviesAdapter

        //history movies recyclerview
        val historyMoviesLayoutManager = LinearLayoutManager(context)
        historyMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        history_recyclerview.layoutManager = historyMoviesLayoutManager
        history_recyclerview.adapter = historyMoviesAdapter

        //documentaries recyclerview
        val documentariesLayoutManager = LinearLayoutManager(context)
        documentariesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        documentary_recyclerview.layoutManager = documentariesLayoutManager
        documentary_recyclerview.adapter = documentariesAdapter

        //romantic movies recyclerview
        val romanticMoviesLayoutManager = LinearLayoutManager(context)
        romanticMoviesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        romantic_recyclerview.layoutManager = romanticMoviesLayoutManager
        romantic_recyclerview.adapter = romanticMoviesAdapter

        //new coming movies data fecth
        mnewComingMoviesApi.fecthNewComingMovies().enqueue(object : Callback<NewComingMoviesMovieList>{
            override fun onResponse(
                call: Call<NewComingMoviesMovieList>,
                response: Response<NewComingMoviesMovieList>
            ) {
                results = response.body()?.results ?: results
                Log.e("movie", results.get(1).title.toString())
                newComingMoviesAdapter.submitList(results)
                newComingMoviesAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<NewComingMoviesMovieList>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

        //top rated movies circular recyclerview fetch data
        val retrofitTopratedMovies = Retrofit.Builder()
           .baseUrl("https://api.themoviedb.org/3/")
           .addConverterFactory(GsonConverterFactory.create()).build()

       val apiTopRatedMovies = retrofitTopratedMovies.create(TopRatedMoviesInterface::class.java)
        apiTopRatedMovies.getTopRatedMovies().enqueue(object : Callback<TopRatedMoviesMovieList>{
            override fun onResponse(
                call: Call<TopRatedMoviesMovieList>,
                response: Response<TopRatedMoviesMovieList>
            ) {
                resultsTopRatedMovies = response.body()?.results ?: resultsTopRatedMovies
                circulerImageAdapter.submitList(resultsTopRatedMovies)
                circulerImageAdapter.notifyDataSetChanged()

                //randomly puts posters in main poster
                posterMovieId = resultsTopRatedMovies.get(random).id
                posterMoviePath = resultsTopRatedMovies.get(random).picturePoster
                val moviePosterURL: String = getString(R.string.POSTER_BASE_URL) + posterMoviePath
                Picasso.get().load(moviePosterURL).error(R.drawable.avenger).into(imageViewPosterMovie)
            }

            override fun onFailure(call: Call<TopRatedMoviesMovieList>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

        //action movies recyclerview fetch data
        val retrofitActionMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiActionMovies = retrofitActionMovies.create(ActionMoviesApi::class.java)
        apiActionMovies.getActionMovies().enqueue(object : Callback<ActionMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<ActionMoviesMovieListModelClass>,
                response: Response<ActionMoviesMovieListModelClass>
            ) {
                resultsActionMovies = response.body()?.results ?: resultsActionMovies
                actionMoviesAdapter.submitList(resultsActionMovies)
                actionMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ActionMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }

        })

        //science fiction movies recyclerview fetch data
        val retrofitScienceFictionMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiScienceFictionMovies = retrofitScienceFictionMovies.create(ScienFictionMoviesApi::class.java)
        apiScienceFictionMovies.getActionMovies().enqueue(object : Callback<ScienceFictionMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<ScienceFictionMoviesMovieListModelClass>,
                response: Response<ScienceFictionMoviesMovieListModelClass>
            ) {
                resultsScienceFictionMovies = response.body()?.results ?: resultsScienceFictionMovies
                scienceFictionMoviesAdaptor.submitList(resultsScienceFictionMovies)
                scienceFictionMoviesAdaptor.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ScienceFictionMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //family movies recyclerview fetch data
        val retrofitFamilyMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiFamilyMovies = retrofitFamilyMovies.create(FamilyMoviesApi::class.java)
        apiFamilyMovies.getFamilyMovies().enqueue(object : Callback<FamilyMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<FamilyMoviesMovieListModelClass>,
                response: Response<FamilyMoviesMovieListModelClass>
            ) {
                resultsFamilyMovies = response.body()?.results ?: resultsFamilyMovies
                familyMoviesAdapter.submitList(resultsFamilyMovies)
                familyMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<FamilyMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //comedy movies recyclerview fetch data
        val retrofitComedyMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiComedyMovies = retrofitComedyMovies.create(ComedyMoviesApi::class.java)
        apiComedyMovies.getComedyMovies().enqueue(object : Callback<ComedyMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<ComedyMoviesMovieListModelClass>,
                response: Response<ComedyMoviesMovieListModelClass>
            ) {
                resultsComedyMovies = response.body()?.results ?: resultsComedyMovies
                comedyMoviesAdapter.submitList(resultsComedyMovies)
                comedyMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ComedyMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //war movies recyclerview fetch data
        val retrofitWarMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiWarMovies = retrofitWarMovies.create(WarMoviesApi::class.java)
        apiWarMovies.getWarMovies().enqueue(object : Callback<WarMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<WarMoviesMovieListModelClass>,
                response: Response<WarMoviesMovieListModelClass>
            ) {
                resultsWarMovies = response.body()?.results ?: resultsWarMovies
                warMoviesAdapter.submitList(resultsWarMovies)
                warMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<WarMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //crime movies recyclerview fetch data
        val retrofitCrimeMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiCrimeMovies = retrofitCrimeMovies.create(CrimeMoviesApi::class.java)
        apiCrimeMovies.getCrimeMovies().enqueue(object : Callback<CrimeMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<CrimeMoviesMovieListModelClass>,
                response: Response<CrimeMoviesMovieListModelClass>
            ) {
                resultsCrimeMovies = response.body()?.results ?: resultsCrimeMovies
                crimeMoviesAdapter.submitList(resultsCrimeMovies)
                crimeMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<CrimeMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //history movies recyclerview fetch data
        val retrofitHistoryMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiHistoryMovies = retrofitHistoryMovies.create(HistoryMoviesApi::class.java)
        apiHistoryMovies.getHistoryMovies().enqueue(object : Callback<HistoryMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<HistoryMoviesMovieListModelClass>,
                response: Response<HistoryMoviesMovieListModelClass>
            ) {
                resultsHistoryMovies = response.body()?.results ?: resultsHistoryMovies
                historyMoviesAdapter.submitList(resultsHistoryMovies)
                historyMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<HistoryMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //documentaries recyclerview fetch data
        val retrofitDocumentaries = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiDocumentaries = retrofitDocumentaries.create(DocumentariesApi::class.java)
        apiDocumentaries.getDocumentaries().enqueue(object : Callback<DocumentariesMovieListModelClass>{
            override fun onResponse(
                call: Call<DocumentariesMovieListModelClass>,
                response: Response<DocumentariesMovieListModelClass>
            ) {
                resultsDocumentaries = response.body()?.results ?: resultsDocumentaries
                documentariesAdapter.submitList(resultsDocumentaries)
                documentariesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<DocumentariesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

        //romantic movies recyclerview fetch data
        val retrofitRomanticMovies = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiRomanticMovies = retrofitRomanticMovies.create(RomanticMoviesApi::class.java)
        apiRomanticMovies.getRomanticMovies().enqueue(object : Callback<RomanticMoviesMovieListModelClass>{
            override fun onResponse(
                call: Call<RomanticMoviesMovieListModelClass>,
                response: Response<RomanticMoviesMovieListModelClass>
            ) {
                resultsRomanticMovies = response.body()?.results ?: resultsRomanticMovies
                romanticMoviesAdapter.submitList(resultsRomanticMovies)
                romanticMoviesAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<RomanticMoviesMovieListModelClass>, t: Throwable) {
                t.message?.let { Log.e("movie", it) }
            }
        })

    }



    fun newComingMoviesClickListener(newComingMoviesModelClass: NewComingMoviesModelClassNew){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", newComingMoviesModelClass.id)
        startActivity(intent)
    }

    fun circularMoviesClickListener(circularMovieModelClass: CircularMovieModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", circularMovieModelClass.id)
        startActivity(intent)
    }

    fun actionMoviesClickListener(actionMoviesModelClass: ActionMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", actionMoviesModelClass.id)
        startActivity(intent)
    }

    fun scienceFictionMoviesClickListener(scienceFictionMoviesModelClass: ScienceFictionMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", scienceFictionMoviesModelClass.id)
        startActivity(intent)
    }

    fun familyMoviesClickListener(familyMoviesModelClass: FamilyMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", familyMoviesModelClass.id)
        startActivity(intent)
    }

    fun comedyMoviesClickListener(comedyMoviesModelClass: ComedyMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", comedyMoviesModelClass.id)
        startActivity(intent)
    }

    fun warMoviesClickListener(warMoviesModelClass: WarMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", warMoviesModelClass.id)
        startActivity(intent)
    }

    fun crimeMoviesClickListener(crimeMoviesModelClass: CrimeMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", crimeMoviesModelClass.id)
        startActivity(intent)
    }

    fun historyMoviesClickListener(historyMoviesModelClass: HistoryMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", historyMoviesModelClass.id)
        startActivity(intent)
    }

    fun documentariesClickListener(documentariesModelClass: DocumentariesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", documentariesModelClass.id)
        startActivity(intent)
    }

    fun romanticMoviesClickListener(romanticMoviesModelClass: RomanticMoviesModelClass){
        val intent = Intent(context, MovieInfoActivity::class.java)
        intent.putExtra("movieName", romanticMoviesModelClass.id)
        startActivity(intent)
    }

}
