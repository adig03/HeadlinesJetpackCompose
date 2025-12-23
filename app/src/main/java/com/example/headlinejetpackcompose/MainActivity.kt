package com.example.headlinejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.headlinejetpackcompose.Navigation.AppNavHost
import com.example.headlinejetpackcompose.theme.HeadlineJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeadlineJetpackComposeTheme {

                AppNavHost()

            }
        }
    }
}



