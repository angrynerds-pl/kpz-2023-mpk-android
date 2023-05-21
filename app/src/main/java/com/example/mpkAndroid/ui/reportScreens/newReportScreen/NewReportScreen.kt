package com.example.mpkAndroid.ui.reportScreens.newReportScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel

@Composable
fun NewReportScreen(
    mapScreenViewModel: MapScreenViewModel,
    newReportScreenViewModel: NewReportScreenViewModel = hiltViewModel(),
    navController: NavController
) {
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
                text = "Nowe zgłoszenie",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                fontSize = 24.sp
            )
            TextField(
                value = mapScreenViewModel.uiState.collectAsState().value.user?.username.orEmpty(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Autor") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
            TextField(
                value = mapScreenViewModel.uiState.collectAsState().value.newReportPosition?.latitude.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Szerokość geograficzna") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = mapScreenViewModel.uiState.collectAsState().value.newReportPosition?.longitude.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Długość geograficzna") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            ReportTypeDropdown(
                onSelectionChange = { newReportScreenViewModel.updateType(it) },
                initialSelection = newReportScreenViewModel.uiState.collectAsState().value.type,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                val newReportData = mapScreenViewModel.uiState.value
                if(newReportData.newReportPosition != null && newReportData.user != null)
                {
                    val report = Report(
                        authorEmail = newReportData.user.email,
                        authorName = newReportData.user.username,
                        latitude = newReportData.newReportPosition.latitude,
                        longitude = newReportData.newReportPosition.longitude,
                        type = newReportScreenViewModel.uiState.value.type
                    )
                    newReportScreenViewModel.addNewReport(report)
                }
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Dodaj")
        }
    }
}
