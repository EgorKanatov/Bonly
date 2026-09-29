package com.example.bonly.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.bonly.R

sealed class BottomNavItem(
    val screen: Screen,
    val title: String,
    @DrawableRes val icon: Int
) {
    data object Calculator : BottomNavItem(
        screen = Screen.Calculator,
        title = "Калькулятор",
        icon = R.drawable.bar_chart_24px
    )

    data object Reports : BottomNavItem(
        screen = Screen.Reports,
        title = "Отчеты",
        icon = R.drawable.save_24px
    )
}
