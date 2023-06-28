package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.repository.NewsFeedRepository
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke() = repository.getRecommendations()
}