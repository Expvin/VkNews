package com.expv1n.vknews.presentation.comments

import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    object Loading : CommentsScreenState()

    data class Comments(val feedPost: FeedPost,
                        val comments: List<PostComment>) : CommentsScreenState()

}
