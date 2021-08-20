package com.rivaldy.id.mvvmtemplateapp.ui

import androidx.activity.viewModels
import com.rivaldy.id.mvvmtemplateapp.base.BaseActivity
import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.ZERO_DATA
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExceptions.handleApiError
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExtensions.isVisible
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExtensions.myToast
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
        binding.noDataRemoteTV.isVisible(movieResponse.movieResults?.size == ZERO_DATA)
        val listData = mutableListOf<MovieLocaleData>()
        for (data in movieResponse.movieResults ?: return) {
            val movieLocaleData = MovieLocaleData(data.id, data.originalTitle, data.overview, data.posterPath)
            listData.add(movieLocaleData)
        }
        movieRemoteAdapter.submitList(listData)
        viewModel.insertMoviesLocal(movieResponse)
    }

    private fun showViewLocale(listMovie: MutableList<MovieEntity>?) {
        binding.noDataLocaleTV.isVisible(listMovie?.size == ZERO_DATA)
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