package com.expv1n.vknews.ui.theme

sealed class AuthState {

    object Authorized : AuthState()

    object NotAuthorized : AuthState()

    object Initial : AuthState()
}