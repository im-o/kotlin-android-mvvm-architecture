package com.rivaldy.id.mvvmtemplateapp.ui.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainMovieActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainMovieViewModel>()
    private lateinit var binding: ActivityMainMovieBinding
    private val movieAdapter: MainMovieAdapter by lazy { MainMovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initObservers()
    }

    private fun initView() {
        binding.listDataRemoteRV.adapter = movieAdapter
        viewModel.getMoviesByPageApiCall()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.moviesFlow.collectLatest {
                movieAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            movieAdapter.loadStateFlow.collect {
                binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                binding.appendProgress.isVisible = it.source.append is LoadState.Loading
            }
        }
    }
}