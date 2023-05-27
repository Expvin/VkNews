package com.expv1n.vknews.data.repository

import android.app.Application
import com.expv1n.vknews.data.mapper.NewsFeedMapper
import com.expv1n.vknews.data.network.ApiFactory
import com.expv1n.vknews.domain.FeedPost
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiFactory = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    suspend fun loadRecomendation(): List<FeedPost> {
        val response = apiFactory.loadRecommendations(getAccessToken())
        return mapper.mapResponseToPosts(response)
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw RuntimeException("Token is null")
    }

    suspend fun addLike(feedPost: FeedPost) {
        apiFactory.addLike(
            token = getAccessToken(),
            owner_id = feedPost.communityId,
            postId = feedPost.id
        )
    }
}