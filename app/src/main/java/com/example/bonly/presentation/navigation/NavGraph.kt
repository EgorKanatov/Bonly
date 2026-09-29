package com.example.bonly.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bonly.presentation.CalculatorViewModel

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
            val viewModel: CalculatorViewModel = hiltViewModel()
            Calculator(viewModel = viewModel)
        }


        composable<Screen.Reports> {
            ReportsScreen()
        }
    }
}
