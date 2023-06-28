package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.repository.NewsFeedRepository

class GetAuthStateFlowUseCase(
    private val repository: NewsFeedRepository
) {

    operator fun invoke() = repository.getAuthStateFlow()
}