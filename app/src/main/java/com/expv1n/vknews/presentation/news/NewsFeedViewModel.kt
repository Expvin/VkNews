package com.expv1n.vknews.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(

    private val getRecommendationUseCase: GetRecommendationUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {


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