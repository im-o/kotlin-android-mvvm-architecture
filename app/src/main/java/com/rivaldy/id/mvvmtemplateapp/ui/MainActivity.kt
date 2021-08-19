package com.rivaldy.id.mvvmtemplateapp.ui

import androidx.activity.viewModels
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.base.BaseActivity
import com.rivaldy.id.mvvmtemplateapp.data.model.api.movie.MovieResponse
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExceptions.handleApiError
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExtensions.myToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val viewModel by viewModels<MovieViewModel>()

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        initClick()
    }

    override fun initObservers() {
        viewModel.getMoviesApiCall()
        viewModel.movies.observe(this, {
            when (it) {
                is DataResource.Loading -> showLoading(true)
                is DataResource.Success -> showView(it.value)
                is DataResource.Failure -> showFailure(it)
            }
        })
        viewModel.getMoviesLocal().observe(this) {
            var text: String? = ""
            for (data in it) text += getString(R.string.movie_desc, data.id, data.title)
            if (it.size > 0) binding.localDataTV.text = text else binding.localDataTV.text = getString(R.string.empty_data)
        }
    }

    override fun showFailure(failure: DataResource.Failure) {
        showLoading(false)
        handleApiError(failure)
    }

    private fun showView(movieResponse: MovieResponse) {
        var text: String? = ""
        for (data in movieResponse.movieResults ?: return) text += getString(R.string.movie_desc, data.id, data.title)
        binding.remoteDataTV.text = text
        viewModel.insertMoviesLocal(movieResponse)

        myToast(getString(R.string.show_data, movieResponse.totalResults.toString()))
        showLoading(false)
    }

    private fun initClick() {
        binding.hintRemoteDataTV.setOnClickListener {
            binding.remoteDataTV.text = getString(R.string.empty_data)
            viewModel.getMoviesApiCall()
        }
        binding.hintLocalDataTV.setOnClickListener { viewModel.clearMovies() }
    }
}