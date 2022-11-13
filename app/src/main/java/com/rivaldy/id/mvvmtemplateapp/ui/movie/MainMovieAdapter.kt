package com.rivaldy.id.mvvmtemplateapp.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rivaldy.id.mvvmtemplateapp.R
import com.rivaldy.id.mvvmtemplateapp.data.model.db.movie.MovieEntity
import com.rivaldy.id.mvvmtemplateapp.databinding.RowItemMovieBinding
import com.rivaldy.id.mvvmtemplateapp.utils.UtilConstants.BASE_IMAGE_URL

/**
 * Created by rivaldy on 09/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

class MainMovieAdapter : PagingDataAdapter<MovieEntity, MainMovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    inner class ViewHolder(private val binding: RowItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: MovieEntity?) {
            binding.apply {
                Glide.with(root.context)
                    .load(BASE_IMAGE_URL + item?.backdropPath)
                    .placeholder(R.color.colorDividerHigh)
                    .into(posterIV)
                movieNameTV.text = item?.title
                movieDescTV.text = item?.overview
            }
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}