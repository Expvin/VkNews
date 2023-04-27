package com.expv1n.vknews.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expv1n.vknews.R

@Composable
fun PostCard() {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            PostHeader()
            Spacer(modifier = Modifier.width(8.dp))
            Image(modifier = Modifier
                .fillMaxWidth(),
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = null,
                contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.width(8.dp))
            Statistics()
        }
    }
}

@Composable
private fun PostHeader() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(modifier = Modifier
            .size(50.dp)
            .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "dev/null",
                color = MaterialTheme.colorScheme.onBackground)
            Text(text = "14:00",
                color = MaterialTheme.colorScheme.onBackground)
        }
        Icon(imageVector = Icons.Rounded.MoreVert,
            contentDescription = null)
    }
}

@Composable
private fun Statistics() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f)) {
            IconWithText(iconResId = R.drawable.ic_views_count,
                text = "1950")
        }
        Row(modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            IconWithText(iconResId = R.drawable.ic_share,
                text = "5")
            IconWithText(iconResId = R.drawable.ic_comment,
                text = "15")
            IconWithText(iconResId = R.drawable.ic_like,
                text = "36")
        }
    }
}

@Composable
private fun IconWithText(iconResId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text,
            color = MaterialTheme.colorScheme.onBackground)
    }
}

@Preview
@Composable
private fun PreviewPostCardLight() {
    VkNewsTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
private fun PreviewPostCardDark() {
    VkNewsTheme(darkTheme = true) {
        PostCard()
    }
}