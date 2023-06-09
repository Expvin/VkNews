package com.expv1n.vknews.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.expv1n.vknews.VkNewsApplication
import com.expv1n.vknews.domain.AuthState
import com.expv1n.vknews.presentation.ViewModelFactory
import com.expv1n.vknews.ui.theme.VkNewsTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as VkNewsApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel(factory = viewModelFactory)
                    val authState = viewModel.authState.collectAsState(AuthState.Initial)
                    val launcher = rememberLauncherForActivityResult(
                        contract = VK.getVKAuthActivityResultContract() ) {
                            viewModel.performAuthResult()
                    }

                    when (authState.value) {
                        is AuthState.Authorized -> {
                            MainScreen(viewModelFactory)
                        }
                        is AuthState.NotAuthorized -> {
                            LoginScreen {
                                launcher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                            }
                        }
                        else -> {

                        }
                    }

                }
            }
        }
    }
}