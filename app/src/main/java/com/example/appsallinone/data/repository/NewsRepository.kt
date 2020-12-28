package com.example.appsallinone.data.repository

import com.example.appsallinone.data.model.ActionState
import com.example.appsallinone.data.model.News
import com.example.appsallinone.data.remote.NewsService
import com.example.appsallinone.data.remote.RetrofitApi
import retrofit2.await

class NewsRepository {
    private val newsService: NewsService by lazy { RetrofitApi.newsService() }

    suspend fun listNews() : ActionState<List<News>> {
        return try {
            val list = newsService.listNews().await()
            ActionState(list.results)
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun detailNews(url: String) : ActionState<News> {
        return try {
            val list = newsService.detailNews(url).await()
            ActionState(list.results.first())
        } catch (e: Exception) {
            ActionState(message = e.message, isSuccess = false)
        }
    }

    suspend fun searchNews(query : String) : ActionState<List<News>> {
        return try {
            val list = newsService.searchNews(query).await()
            ActionState(list.results)
        } catch (e: Exception){
            ActionState(message = e.message, isSuccess = false)
        }
    }
}