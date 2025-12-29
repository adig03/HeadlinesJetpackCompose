package com.example.headlinejetpackcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.headlinejetpackcompose.presentation.Search.SearchScreen
import com.example.headlinejetpackcompose.presentation.Search.SearchState
import com.example.headlinejetpackcompose.presentation.Search.SearchViewModel
import com.example.headlinejetpackcompose.presentation.home.HomeScreen
import com.example.headlinejetpackcompose.presentation.onboardingScreen.OnboardingScreen
import com.example.headlinejetpackcompose.presentation.splashScreen.SplashScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen
    ) {

        composable(Routes.SplashScreen) {
            SplashScreen(navController)
        }

        composable(Routes.OnboardingScreen) {
            OnboardingScreen(navController)
        }

        composable(Routes.HomeScreen) {
            HomeScreen(navController)
        }

        // ✅ Search destination
        composable(Routes.SearchScreen) {

            // ✅ ViewModel MUST be inside composable
            val viewModel: SearchViewModel = hiltViewModel()

            SearchScreen(
                state = viewModel.state,
                event = viewModel::onEvent
            )
        }
    }
}


