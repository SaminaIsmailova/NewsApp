package com.example.newsapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: GetNewsUseCase) : ViewModel() {
    suspend fun getAll(): LiveData<List<NewsEntity>> = useCase.getAll()
    suspend fun delete(newsEntity: NewsEntity) = useCase.insert(newsEntity)
}