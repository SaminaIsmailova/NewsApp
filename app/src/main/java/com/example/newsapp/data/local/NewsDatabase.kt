package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.local.models.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}