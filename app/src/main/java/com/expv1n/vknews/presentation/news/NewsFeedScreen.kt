package com.expv1n.vknews.presentation.news

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.expv1n.vknews.domain.FeedPost


@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
    onCommentsClickListener: (FeedPost) -> Unit
) {
    val viewModel: NewsFeedViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)

    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPost(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentsClickListener = onCommentsClickListener
            )
        }

        NewsFeedScreenState.Initial -> {

        }
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPost(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentsClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = Color.LightGray),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 36.dp
        ), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = { it.id }) { feedPost ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart)
            ) {
                PostCard(modifier = Modifier,
                    feedPost = feedPost,
                    onViewsClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    },
                    onSharesClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    },
                    onCommentsClickListener = {
                        onCommentsClickListener(feedPost)
                    },
                    onLikesClickListener = { _ ->
                        viewModel.changeLikeStatus(feedPost)
                    })
            }
        }
    }
}