package com.rivaldy.id.pharmacist.ui

import androidx.activity.viewModels
import com.rivaldy.id.pharmacist.base.BaseActivity
import com.rivaldy.id.pharmacist.data.model.api.movie.MovieResponse
import com.rivaldy.id.pharmacist.data.model.db.movie.MovieEntity
import com.rivaldy.id.pharmacist.data.model.offline.MovieLocaleData
import com.rivaldy.id.pharmacist.data.network.DataResource
import com.rivaldy.id.pharmacist.databinding.ActivityMainBinding
import com.rivaldy.id.pharmacist.utils.UtilConstants.ZERO_DATA
import com.rivaldy.id.pharmacist.utils.UtilExceptions.handleApiError
import com.rivaldy.id.pharmacist.utils.UtilExtensions.isAreVisible
import com.rivaldy.id.pharmacist.utils.UtilExtensions.myToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MovieViewModel>()
    private val movieRemoteAdapter: MovieAdapter by lazy { MovieAdapter { item -> movieClick(item) } }
    private val movieLocaleAdapter: MovieAdapter by lazy { MovieAdapter { item -> movieClick(item) } }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        binding.listDataRemoteRV.adapter = movieRemoteAdapter
        binding.listDataLocaleRV.adapter = movieLocaleAdapter
        initClick()
    }

    override fun initObservers() {
        viewModel.getMoviesApiCall()
        viewModel.movies.observe(this, {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showViewRemote(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })
        viewModel.getMoviesLocal().observe(this) {
            showViewLocale(it)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        handleApiError(failure)
    }

    private fun showViewRemote(movieResponse: MovieResponse) {
        showLoading(false)
        binding.noDataRemoteTV.isAreVisible(movieResponse.movieResults?.size == ZERO_DATA)
        val listData = mutableListOf<MovieLocaleData>()
        for (data in movieResponse.movieResults ?: return) {
            val movieLocaleData = MovieLocaleData(data.id, data.originalTitle, data.overview, data.posterPath)
            listData.add(movieLocaleData)
        }
        movieRemoteAdapter.submitList(listData)
        viewModel.insertMoviesLocal(movieResponse)
    }

    private fun showViewLocale(listMovie: MutableList<MovieEntity>?) {
        binding.noDataLocaleTV.isAreVisible(listMovie?.size == ZERO_DATA)
        val listData = mutableListOf<MovieLocaleData>()
        for (data in listMovie ?: return) {
            val movieLocaleData = MovieLocaleData(data.id, data.title, data.overview, data.backdropPath)
            listData.add(movieLocaleData)
        }
        movieLocaleAdapter.submitList(listData)
    }

    private fun initClick() {
        binding.hintRemoteDataTV.setOnClickListener { viewModel.getMoviesApiCall() }
        binding.hintLocaleDataTV.setOnClickListener { viewModel.clearMovies() }
    }

    private fun movieClick(item: MovieLocaleData) {
        myToast(item.toString())
    }
}