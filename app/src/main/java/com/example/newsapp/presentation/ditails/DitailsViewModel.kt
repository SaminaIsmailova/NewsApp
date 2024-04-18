package com.example.newsapp.presentation.ditails

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.local.models.NewsEntity
import com.example.newsapp.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DitailsViewModel @Inject constructor(private val useCase: GetNewsUseCase):ViewModel() {

    suspend fun insert(newsEntity: NewsEntity) = useCase.insert(newsEntity)
}