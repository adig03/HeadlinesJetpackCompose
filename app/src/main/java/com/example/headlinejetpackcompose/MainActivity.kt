package com.example.headlinejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.headlinejetpackcompose.Navigation.AppNavHost
import com.example.headlinejetpackcompose.theme.HeadlineJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {



            HeadlineJetpackComposeTheme {

                AppNavHost()

            }
        }
    }
}



