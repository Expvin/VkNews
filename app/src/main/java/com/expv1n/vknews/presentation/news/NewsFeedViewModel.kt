package com.expv1n.vknews.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.expv1n.vknews.data.repository.NewsFeedRepositoryImp
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.usecases.ChangeLikeStatusUseCase
import com.expv1n.vknews.domain.usecases.DeletePostUseCase
import com.expv1n.vknews.domain.usecases.GetRecommendationUseCase
import com.expv1n.vknews.domain.usecases.LoadNextDataUseCase
import com.expv1n.vknews.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImp(application)

    private val getRecommendationUseCase = GetRecommendationUseCase(repository)
    private val loadNextDataUseCase = LoadNextDataUseCase(repository)
    private val changeLikeStatusUseCase = ChangeLikeStatusUseCase(repository)
    private val deletePostUseCase = DeletePostUseCase(repository)
    private val recommendationFlow = getRecommendationUseCase()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d("NewsFeedViewModel", "Exception caught")
    }

    //    private val loadNextDataSharedFlow = MutableSharedFlow<Unit>()
//    private val loadNextDataFlow = flow {
//        loadNextDataSharedFlow.collect {
//            emit(
//                NewsFeedScreenState.Posts(
//                    posts = recommendationFlow.value,
//                    nextDataIsLoading = true
//                )
//            )
//        }
//    }
    private val loadNextDataFlow = MutableSharedFlow<NewsFeedScreenState>()

    val screenState = recommendationFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(posts = it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
//            loadNextDataSharedFlow.emit(Unit)
            loadNextDataFlow.emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationFlow.value, nextDataIsLoading = true
                )
            )
            loadNextDataUseCase()
        }

    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }

}