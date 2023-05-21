package com.example.mpkAndroid.ui.reportScreens.newReportScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel

@Composable
fun NewReportScreen(
    mapScreenViewModel: MapScreenViewModel,
    newReportScreenViewModel: NewReportScreenViewModel = hiltViewModel<NewReportScreenViewModel>(),
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Cofnij")
        }
    }
}