package com.example.mpkAndroid.ui.reportScreens.reportDetailsScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel

@Composable
fun ReportDetailsScreen(
    mapScreenViewModel: MapScreenViewModel,
    reportDetailsScreenViewModel: ReportDetailsScreenViewModel = hiltViewModel<ReportDetailsScreenViewModel>(),
    navController: NavController
) {
}