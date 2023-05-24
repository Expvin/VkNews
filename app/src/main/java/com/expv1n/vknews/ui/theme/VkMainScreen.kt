package com.expv1n.vknews.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.expv1n.vknews.navigation.AppNavGraph
import com.expv1n.vknews.navigation.rememberNavigationState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    Scaffold(bottomBar = {
        NavigationBar(containerColor = Color.White) {

            val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
            val items = listOf(
                NavigationItem.Home,
                NavigationItem.Favourite,
                NavigationItem.Profile
            )
            items.forEach { item ->
                val selected = navBackStackEntry?.destination?.hierarchy?.any {
                    it.route == item.screen.route
                } ?: false
                NavigationBarItem(selected = selected,
                    onClick = {
                        if (!selected) {
                            navigationState.navigateTo(item.screen.route)
                        }
                      },
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
            feedPostScreenContent = {
                HomeScreen(paddingValues = paddingValues,
                    onCommentsClickListener = {
                        navigationState.navigateToComments(feedPost = it)
                    })
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = { navigationState.navHostController.popBackStack() },
                    feedPost = feedPost
                )
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
