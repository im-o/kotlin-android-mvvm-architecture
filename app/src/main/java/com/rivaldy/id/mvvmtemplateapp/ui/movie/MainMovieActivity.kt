package com.rivaldy.id.mvvmtemplateapp.ui.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainMovieBinding
import dagger.hilt.android.AndroidEntryPoint

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
        binding.listDataRemoteRV.adapter = movieAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter { movieAdapter.retry() }
        )
    }

    private fun initObservers() {
        viewModel.moviesApiCall.observe(this) {
            movieAdapter.submitData(lifecycle, it)
        }
    }
}