package com.example.mpkAndroid.ui.reportScreens.newReportScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel

@Composable
fun NewReportScreen(
    mapScreenViewModel: MapScreenViewModel,
    newReportScreenViewModel: NewReportScreenViewModel = hiltViewModel<NewReportScreenViewModel>(),
    navController: NavController
) {
}