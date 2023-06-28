package com.expv1n.vknews.presentation.comments

import androidx.lifecycle.ViewModel
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    val screenState= getCommentsUseCase(feedPost)
        .map { CommentsScreenState.Comments(feedPost = feedPost, comments = it) as CommentsScreenState }
        .onStart { emit(CommentsScreenState.Loading) }
}
