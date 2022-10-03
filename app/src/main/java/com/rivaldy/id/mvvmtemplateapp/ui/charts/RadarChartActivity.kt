package com.rivaldy.id.mvvmtemplateapp.ui.charts

import android.graphics.Color
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import com.rivaldy.id.mvvmtemplateapp.base.BaseActivity
import com.rivaldy.id.mvvmtemplateapp.data.network.DataResource
import com.rivaldy.id.mvvmtemplateapp.databinding.ActivityRadarChartBinding


class RadarChartActivity : BaseActivity<ActivityRadarChartBinding>() {
    private val chartItems = arrayOf("Burger", "Steak", "Apple", "Orange", "Chicken")

    override fun getViewBinding() = ActivityRadarChartBinding.inflate(layoutInflater)

    override fun initView() {
        initChart()
    }

    private fun initChart() {
        binding.apply {
            chart.setBackgroundColor(Color.rgb(60, 65, 82))

            chart.description.isEnabled = false

            chart.webLineWidth = 1f
            chart.webColor = Color.LTGRAY
            chart.webLineWidthInner = 1f
            chart.webColorInner = Color.LTGRAY
            chart.webAlpha = 100

            setData()
            chart.animateXY(1400, 1400, Easing.EaseInOutQuad)
            val xAxis = chart.xAxis
            xAxis.textSize = 9f
            xAxis.yOffset = 0f
            xAxis.xOffset = 0f
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return chartItems[value.toInt() % chartItems.size]
                }
            }
            xAxis.textColor = Color.WHITE

            val yAxis = chart.yAxis
            yAxis.setLabelCount(chartItems.size, false)
            yAxis.textSize = 9f
            yAxis.axisMinimum = 0f
            yAxis.axisMaximum = 80f
            yAxis.setDrawLabels(false)

            val l = chart.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.xEntrySpace = 7f
            l.yEntrySpace = 5f
            l.textColor = Color.WHITE
        }
    }

    private fun setData() {
        val mul = 80f
        val min = 20f
        val cnt = chartItems.size
        val entries1: ArrayList<RadarEntry> = ArrayList()
        val entries2: ArrayList<RadarEntry> = ArrayList()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (i in 0 until cnt) {
            val val1 = (Math.random() * mul).toFloat() + min
            entries1.add(RadarEntry(val1))
            val val2 = (Math.random() * mul).toFloat() + min
            entries2.add(RadarEntry(val2))
        }
        val set1 = RadarDataSet(entries1, "Last Week")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.rgb(103, 110, 129)
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)
        val set2 = RadarDataSet(entries2, "This Week")
        set2.color = Color.rgb(121, 162, 175)
        set2.fillColor = Color.rgb(121, 162, 175)
        set2.setDrawFilled(true)
        set2.fillAlpha = 180
        set2.lineWidth = 2f
        set2.isDrawHighlightCircleEnabled = true
        set2.setDrawHighlightIndicators(false)
        val sets: ArrayList<IRadarDataSet> = ArrayList()
        sets.add(set1)
        sets.add(set2)
        val data = RadarData(sets)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)
        binding.chart.data = data
        binding.chart.invalidate()
    }

    override fun initObservers() {
    }

    override fun showFailure(failure: DataResource.Failure) {
    }

}