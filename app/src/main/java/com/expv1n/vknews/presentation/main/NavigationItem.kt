package com.expv1n.vknews.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.expv1n.vknews.R
import com.expv1n.vknews.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val vectorImage: ImageVector
) {
    object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.bottom_item_home,
        vectorImage = Icons.Outlined.Home
    )

    object Favourite : NavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.bottom_item_favourite,
        vectorImage = Icons.Outlined.Favorite
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.bottom_item_profile,
        vectorImage = Icons.Outlined.Person
    )
}