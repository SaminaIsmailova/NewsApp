package com.example.newsapp.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.NewsModel
import com.example.newsapp.databinding.ItemNewsBinding
import kotlin.reflect.KFunction0

class NewsAdapter(private val onClick: (newsModel:Article)-> Unit) : ListAdapter<Article, NewsViewHolder>(
    NewsDiffUtil()
) {
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

class NewsViewHolder(private val binding: ItemNewsBinding) : ViewHolder(binding.root) {
    fun bind(newsModel: Article) {
        binding.image.load(newsModel.imageUrl)
        binding.tvTitle.text = newsModel.title
        binding.image.isVisible = newsModel.imageUrl != ""
        binding.tvCreator.text = newsModel.creator.toString()
        binding.tvDateOfRelease.text = newsModel.pubDate
        binding.tvKeyword.text = newsModel.keywords.toString()
    }
}

class NewsDiffUtil : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.articleId == newItem.articleId
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

}