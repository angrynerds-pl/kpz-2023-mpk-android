package com.example.mpkAndroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mpkAndroid.ui.mapScreen.MapScreen
import com.example.mpkAndroid.ui.theme.MpkAndroidTheme
import com.example.mpkAndroid.ui.vehiclesFilterScreen.VehiclesFilterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MpkAndroidTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                        NavHost(navController = navController, startDestination = "map"){
                            composable("map") { MapScreen(navController = navController) }
                            composable("vehiclesFilter") { VehiclesFilterScreen(navController = navController)}
                        }

                    
                }
            }
        }
    }
}
