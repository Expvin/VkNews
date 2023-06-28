package com.expv1n.vknews

import android.app.Application
import com.expv1n.vknews.di.DaggerApplicationComponent
import com.expv1n.vknews.domain.FeedPost

class VkNewsApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(
            this,
            FeedPost(
                0, 1, "", "",
                "", "",
                "", listOf(), false
            )
        )
    }

}