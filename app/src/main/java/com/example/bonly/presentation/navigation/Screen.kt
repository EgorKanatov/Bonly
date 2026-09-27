package com.example.bonly.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Calculator : Screen

    @Serializable
    data object Reports : Screen
}