package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() = repository.loadNextData()
}