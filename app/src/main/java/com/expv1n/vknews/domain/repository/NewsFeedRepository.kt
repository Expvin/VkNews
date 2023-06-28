package com.expv1n.vknews.domain.repository

import com.expv1n.vknews.domain.AuthState
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {
    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun deletePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}