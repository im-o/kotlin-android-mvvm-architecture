package com.rivaldy.id.mvvmtemplateapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityMainBinding
import com.rivaldy.id.mvvmtemplateapp.ui.charts.RadarChartActivity
import com.rivaldy.id.mvvmtemplateapp.ui.movie.MainMovieActivity
import com.rivaldy.id.mvvmtemplateapp.ui.sample_movie.SampleMovieActivity
import com.rivaldy.id.mvvmtemplateapp.utils.UtilExtensions.openActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClick()
    }

    private fun initClick() {
        binding.showPagingMovieMB.setOnClickListener {
            openActivity(MainMovieActivity::class.java)
        }
        binding.showSampleMovieMB.setOnClickListener {
            openActivity(SampleMovieActivity::class.java)
        }
        binding.radarChartMB.setOnClickListener {
            openActivity(RadarChartActivity::class.java)
        }
    }
}