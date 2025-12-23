package com.example.headlinejetpackcompose.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.headlinejetpackcompose.ui.HomeScreen
import com.example.headlinejetpackcompose.ui.OnboardingScreen
import com.example.headlinejetpackcompose.ui.SplashScreen

@Composable
fun AppNavHost(){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.SplashScreen){

        composable(Routes.SplashScreen){
            SplashScreen(navController)
        }

        composable(Routes.OnboardingScreen){
            OnboardingScreen(navController)
        }

        composable(Routes.HomeScreen){
            HomeScreen(navController)
        }
    }

}

