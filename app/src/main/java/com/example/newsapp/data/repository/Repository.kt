package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.base.BaseRepository
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.data.models.NewsModel
import com.example.newsapp.data.server.NewsApiService
import com.example.newsapp.data.utils.Resource
import com.example.newsapp.domain.repositories.NewsRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: NewsApiService,
    private val db: NewsDatabase
) : BaseRepository(), NewsRepository {
    override suspend fun getNews(): LiveData<Resource<NewsModel>> = performRequest {
        api.getNews().body()!!
    }

    override suspend fun insert(newsEntity: NewsEntity) {
        db.getNewsDao().insert(newsEntity)
    }

    override suspend fun delete(newsEntity: NewsEntity) {
        db.getNewsDao().delete(newsEntity)
    }

    override suspend fun getAll(): LiveData<List<NewsEntity>> = db.getNewsDao().getAll()

}