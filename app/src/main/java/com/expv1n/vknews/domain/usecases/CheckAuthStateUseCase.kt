package com.expv1n.vknews.domain.usecases

import com.expv1n.vknews.domain.repository.NewsFeedRepository

class CheckAuthStateUseCase(
    private val repository: NewsFeedRepository)
{
    suspend operator fun invoke() = repository.checkAuthState()
}