package com.example.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.data.local.models.NewsEntity

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(newsEntity: NewsEntity)

    @Delete
    suspend fun delete(newsEntity: NewsEntity)

    @Query("SELECT*FROM news")
    fun getAll(): LiveData<List<NewsEntity>>
}