package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.repository.NewsFeedRepository

class ChangeLikeStatusUseCase(
    private val repository: NewsFeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) = repository.changeLikeStatus(feedPost)

}