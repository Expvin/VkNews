package com.expv1n.vknews.domain

import com.expv1n.vknews.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 1000),
        StatisticItem(StatisticType.SHARES, 15),
        StatisticItem(StatisticType.COMMENTS, 26),
        StatisticItem(StatisticType.LIKES, 54),
    )
)
