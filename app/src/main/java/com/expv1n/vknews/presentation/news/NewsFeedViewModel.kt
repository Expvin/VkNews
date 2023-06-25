package com.expv1n.vknews.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.expv1n.vknews.data.mapper.NewsFeedMapper
import com.expv1n.vknews.data.network.ApiFactory
import com.expv1n.vknews.data.repository.NewsFeedRepository
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.StatisticItem
import com.expv1n.vknews.extensions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(application)
    private val recommendationFlow = repository.recommendation
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
            repository.loadNextDate()
        }

    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.changeLikeStatus(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.deletePost(feedPost)
        }
    }

}