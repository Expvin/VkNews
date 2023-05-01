package com.expv1n.vknews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.expv1n.vknews.ui.theme.MainVkNewsScreen
import com.expv1n.vknews.ui.theme.VkNewsTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainVkNewsScreen(viewModel = viewModel)
                }
            }
        }
    }
}