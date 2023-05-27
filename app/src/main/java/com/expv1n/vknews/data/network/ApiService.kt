package com.expv1n.vknews.data.network

import com.expv1n.vknews.data.model.NewsFeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.131")
    suspend fun loadRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto

    @GET("likes.add?v=5.131&type=post")
    suspend fun addLike(
        @Query("access_token") token: String,
        @Query("owner_id") owner_id: Long,
        @Query("item_id ") postId: Long
    )
}