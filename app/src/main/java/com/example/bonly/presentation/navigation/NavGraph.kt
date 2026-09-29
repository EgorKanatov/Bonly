package com.example.bonly.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Calculator,
        modifier = modifier
    ) {
        composable<Screen.Calculator> {
            CalculatorScreen()
        }

        composable<Screen.Reports> {
            ReportsScreen()
        }
    }
}
