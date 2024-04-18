package com.example.newsapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String? = "",
    val description: String? = "Описание пустое",
    val pubDate: String? = "",
    val imageUrl: String? = "",
    val category: String? = "",
    val creator: String? = "",
    val keyword: String? = "",
    val saved : Boolean? = false
)
