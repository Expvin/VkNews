package com.expv1n.vknews.domain

import com.expv1n.vknews.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author Name",
    val authorAvatarId: Int = R.drawable.person_icon,
    val commentText: String = "Comment long text......",
    val publicationDate: String = "21:00"
)