package com.example.mpkAndroid.ui.reportScreens.reportDetailsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel

@Composable
fun ReportDetailsScreen(
    mapScreenViewModel: MapScreenViewModel,
    reportDetailsScreenViewModel: ReportDetailsScreenViewModel = hiltViewModel<ReportDetailsScreenViewModel>(),
    navController: NavController
) {
    val selectedReport = mapScreenViewModel.getReportDetailsForSelectedMarker()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(bottom = 64.dp) // Adjust bottom padding as needed
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Cofnij")
            }
            Text(
                text = "Szczegóły zgłoszenia",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 24.sp
            )
            TextField(
                value = selectedReport.authorName,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Autor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
            TextField(
                value = selectedReport.latitude.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Szerokość geograficzna") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = selectedReport.longitude.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Długość geograficzna") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = selectedReport.type.translation,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Typ zdarzenia") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}