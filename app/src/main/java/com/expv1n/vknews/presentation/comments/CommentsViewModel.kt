package com.expv1n.vknews.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.expv1n.vknews.data.repository.NewsFeedRepositoryImp
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : ViewModel() {

    private val repository = NewsFeedRepositoryImp(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState= getCommentsUseCase(feedPost)
        .map { CommentsScreenState.Comments(feedPost = feedPost, comments = it) as CommentsScreenState }
        .onStart { emit(CommentsScreenState.Loading) }
}
