package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.repository.NewsFeedRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost) = repository.getComments(feedPost)
}