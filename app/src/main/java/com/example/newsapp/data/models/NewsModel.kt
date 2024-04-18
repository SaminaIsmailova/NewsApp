package com.example.newsapp.data.models

import java.io.Serializable

data class NewsModel(
    val status: String,
    val totalResults: Int,
    val results: List<Article>,
    val nextPage: String
)

data class Article(
    val articleId: String?="",
    val title: String ?="",
    val link: String?="",
    val keywords: List<String>?= listOf(""),
    val creator: List<String>?= listOf(""),
    val videoUrl: String??="",
    val description: String ?="",
    val content: String ?="",
    val pubDate: String ?="",
    val imageUrl: String ?="",
    val sourceId: String?="",
    val sourcePriority: Long,
    val sourceUrl: String?="",
    val sourceIcon: String?="",
    val language: String?="",
    val country: List<String> ?= listOf(""),
    val category: List<String>?=listOf(""),
    val aiTag: List<String>?=listOf(""),
    val aiRegion: List<String>?=listOf(""),
    val sentiment: String?="",
    val sentimentStats: SentimentStats
):Serializable

data class SentimentStats(
    val positive: Double,
    val neutral: Double,
    val negative: Double
)

