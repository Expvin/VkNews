package com.expv1n.vknews.navigation

import android.net.Uri
import com.expv1n.vknews.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favourite : Screen(ROUTE_FAVOURITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Home: Screen(ROUTE_HOME)
    object Comments: Screen(ROUTE_COMMENTS) {

        private const val COMMENTS_FOR_ARGS = "comments"

        fun getRoutWithArgs(feedPost: FeedPost): String {
            val feedPostGson = Gson().toJson(feedPost)
            return "$COMMENTS_FOR_ARGS/${feedPostGson.encode()}"
        }

    }

    companion object {

        const val KEY_FEED_POST = "feed_post"

        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}