package com.rivaldy.id.mvvmtemplateapp.ui.movie

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.rivaldy.id.mvvmtemplateapp.base.BaseActivity
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainMovieBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExceptions.handleApiError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMovieActivity : BaseActivity<ActivityMainMovieBinding>() {
    private val viewModel by viewModels<MainMovieViewModel>()
    private val movieAdapter: MainMovieAdapter by lazy { MainMovieAdapter() }

    override fun getViewBinding() = ActivityMainMovieBinding.inflate(layoutInflater)

    override fun initView() {
        binding.listDataRV.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter { movieAdapter.retry() }
        )
        initListener()
    }

    override fun initObservers() {
        viewModel.moviesApiCall.observe(this) {
            movieAdapter.submitData(lifecycle, it)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        handleApiError(failure)
    }

    private fun initListener() {
        movieAdapter.addLoadStateListener { loadState ->
            showLoading(loadState.refresh is LoadState.Loading && movieAdapter.itemCount <= 0)
            binding.noDataTV.isVisible = loadState.append.endOfPaginationReached && movieAdapter.itemCount <= 0
        }
    }
}