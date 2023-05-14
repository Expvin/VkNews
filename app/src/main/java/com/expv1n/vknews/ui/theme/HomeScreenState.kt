package com.expv1n.vknews.ui.theme

import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.domain.PostComment

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()

    data class Comments(val feedPost: FeedPost,
                        val comments: List<PostComment>) : HomeScreenState()

}
