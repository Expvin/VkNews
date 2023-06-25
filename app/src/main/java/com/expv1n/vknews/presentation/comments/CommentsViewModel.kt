package com.expv1n.vknews.presentation.comments

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.expv1n.vknews.data.repository.NewsFeedRepository
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.PostComment
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState= repository.getComments(feedPost)
        .map { CommentsScreenState.Comments(feedPost = feedPost, comments = it) as CommentsScreenState }
        .onStart { emit(CommentsScreenState.Loading) }
}
