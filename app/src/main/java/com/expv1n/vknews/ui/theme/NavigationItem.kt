package com.expv1n.vknews.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.expv1n.vknews.R

sealed class NavigationItem(
    val titleResId: Int,
    val vectorImage: ImageVector
) {
    object Home: NavigationItem(titleResId = R.string.bottom_item_home,
        vectorImage = Icons.Outlined.Home)

    object Favourite: NavigationItem(titleResId = R.string.bottom_item_favourite,
        vectorImage = Icons.Outlined.Favorite)

    object Profile: NavigationItem(titleResId = R.string.bottom_item_profile,
        vectorImage = Icons.Outlined.Person)
}