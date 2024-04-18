package com.example.newsapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.databinding.ItemNewsBinding

class NewsEntityAdapter (private val onClick: (newsModel: NewsEntity)-> Unit) : ListAdapter<NewsEntity, NewsViewHolder>(NewsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClick(getItem(position))
        }
    }
}

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(newsModel: NewsEntity) {
        binding.image.load(newsModel.imageUrl)
        binding.tvTitle.text = newsModel.title
        binding.image.isVisible = newsModel.imageUrl != ""
        binding.tvCreator.text = newsModel.creator
        binding.tvDateOfRelease.text = newsModel.pubDate
        binding.tvKeyword.text = newsModel.keyword
    }
}

class NewsDiffUtil : DiffUtil.ItemCallback<NewsEntity>() {
    override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
        return oldItem == newItem
    }


}