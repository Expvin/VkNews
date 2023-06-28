package com.expv1n.vknews.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.expv1n.vknews.data.repository.NewsFeedRepositoryImp
import com.expv1n.vknews.domain.usecases.CheckAuthStateUseCase
import com.expv1n.vknews.domain.usecases.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImp(application)

    private val getAuthStateFlowUseCase = GetAuthStateFlowUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateUseCase()
        }
    }
}