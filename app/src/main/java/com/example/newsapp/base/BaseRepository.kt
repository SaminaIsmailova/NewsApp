package com.example.newsapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.newsapp.data.utils.Resource
import kotlinx.coroutines.Dispatchers

abstract class BaseRepository {

    fun <T> performRequest(apiCall: suspend () -> T): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = apiCall.invoke()
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
            }
        }

}