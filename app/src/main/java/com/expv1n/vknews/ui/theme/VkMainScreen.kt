package com.expv1n.vknews.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.expv1n.vknews.domain.FeedPost
import com.expv1n.vknews.navigation.AppNavGraph
import com.expv1n.vknews.navigation.rememberNavigationState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainVkNewsScreen() {

    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(bottomBar = {
        NavigationBar {

            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val currentRout = navBackStackEntry?.destination?.route

            val items = listOf(
                NavigationItem.Home, NavigationItem.Favourite, NavigationItem.Profile
            )
            items.forEach { item ->
                NavigationBarItem(selected = currentRout == item.screen.route,
                    onClick = { navigationState.navigateTo(item.screen.route) },
                    icon = {
                        Icon(item.vectorImage, contentDescription = null)
                    },
                    label = {
                        Text(text = stringResource(id = item.titleResId))
                    })
            }
        }
    }) { paddingValues ->
        AppNavGraph(navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentsClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackPressed = { commentsToPost.value = null },
                        feedPost = commentsToPost.value!!
                    )
                }
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = { TextCounter(name = "Profile") })
    }
}

@Composable
private fun TextCounter(name: String) {
    var counter by rememberSaveable {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { counter++ },
        text = "$name, count: $counter",
        color = Color.Black
    )
}
