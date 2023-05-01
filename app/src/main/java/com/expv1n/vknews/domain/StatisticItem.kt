package com.expv1n.vknews.domain

data class StatisticItem(
    val type: StatisticType,
    val count: Int
)

enum class StatisticType {
    VIEWS, COMMENTS, SHARES, LIKES
}
