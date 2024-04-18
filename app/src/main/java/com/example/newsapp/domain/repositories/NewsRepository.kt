package com.example.newsapp.domain.repositories

import androidx.lifecycle.LiveData
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.data.models.NewsModel
import com.example.newsapp.data.utils.Resource

interface NewsRepository {
    suspend fun getNews():LiveData<Resource<NewsModel>>
    suspend fun insert(newsEntity: NewsEntity)
    suspend fun delete(newsEntity: NewsEntity)
    suspend fun getAll():LiveData<List<NewsEntity>>
}