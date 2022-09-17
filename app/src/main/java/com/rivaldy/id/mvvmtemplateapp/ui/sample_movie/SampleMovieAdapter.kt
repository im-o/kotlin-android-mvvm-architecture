package com.rivaldy.id.mvvmtemplateapp.ui.sample_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.data.model.offline.MovieLocaleData
import com.rivaldy.id.mvvmtemplateapp.databinding.RowItemMovieBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.BASE_IMAGE_URL

/**
 * Created by rivaldy on 09/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class SampleMovieAdapter(
    private val listener: (MovieLocaleData) -> Unit
) : ListAdapter<MovieLocaleData, SampleMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: RowItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: MovieLocaleData) {
            Glide.with(binding.root.context)
                .load(BASE_IMAGE_URL + item.posterPath)
                .placeholder(R.color.colorDividerHigh)
                .into(binding.posterIV)
            binding.movieNameTV.text = item.title
            binding.movieDescTV.text = item.description
            binding.root.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieLocaleData>() {
            override fun areItemsTheSame(oldItem: MovieLocaleData, newItem: MovieLocaleData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieLocaleData, newItem: MovieLocaleData): Boolean {
                return oldItem == newItem
            }
        }
    }
}