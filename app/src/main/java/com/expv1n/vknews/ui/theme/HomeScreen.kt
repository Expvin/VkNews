package com.expv1n.vknews.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.unit.dp
import com.expv1n.vknews.MainViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {
    val feedPost = viewModel.feedPosts.observeAsState(listOf())
    LazyColumn(
        modifier = androidx.compose.ui.Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 8.dp,
            bottom = 36.dp
        ), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = feedPost.value, key = { it.id }) { feedPost ->
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
                PostCard(
                    modifier = Modifier.padding(8.dp),
                    feedPost = feedPost,
                    onViewsClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    },
                    onSharesClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    },
                    onCommentsClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    },
                    onLikesClickListener = { StatisticItem ->
                        viewModel.updateCount(feedPost, StatisticItem)
                    }
                )
            }
        }
    }
}