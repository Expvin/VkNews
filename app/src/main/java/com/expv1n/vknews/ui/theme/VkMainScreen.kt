package com.expv1n.vknews.ui.theme

import android.annotation.SuppressLint
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
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.expv1n.vknews.MainViewModel
import com.expv1n.vknews.domain.StatisticItem


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainVkNewsScreen(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            NavigationBar {

                val selectedItemPosition = remember {
                    mutableStateOf(0)
                }

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemPosition.value == index,
                        onClick = { selectedItemPosition.value = index },
                        icon = {
                            Icon(item.vectorImage, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        }
                    )
                }
            }
        }
    ) {
        val feedPost = viewModel.feedPosts.observeAsState(listOf())
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                start = 8.dp,
                top = 16.dp,
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

}
