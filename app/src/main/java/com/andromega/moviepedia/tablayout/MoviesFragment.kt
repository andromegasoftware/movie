package com.andromega.moviepedia.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.andromega.moviepedia.R
import com.andromega.moviepedia.movies.*
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private lateinit var circulerImageAdapter: CirculerImageAdapter
    private lateinit var newComingMoviesAdapter: NewComingMoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        circulerImageAdapter = CirculerImageAdapter()
        newComingMoviesAdapter = NewComingMoviesAdapter()

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

        addDataSet()
        addDataSetNewComingMoviesInfo()

    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        circulerImageAdapter.submitList(data)
    }

    private fun addDataSetNewComingMoviesInfo(){
        val data = DataSourceMoviesInfo.createDataSet()
        newComingMoviesAdapter.submitList(data)
    }
}