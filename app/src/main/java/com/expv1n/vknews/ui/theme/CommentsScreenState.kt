package com.expv1n.vknews.ui.theme

import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(val feedPost: FeedPost,
                        val comments: List<PostComment>) : CommentsScreenState()

}
