package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.repository.NewsFeedRepository

class GetRecommendationUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke() = repository.getRecommendations()
}