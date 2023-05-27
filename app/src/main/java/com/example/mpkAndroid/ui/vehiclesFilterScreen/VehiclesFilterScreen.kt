@file:OptIn(ExperimentalLayoutApi::class)

package com.example.mpkAndroid.ui.vehiclesFilterScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mpkAndroid.ui.mapScreen.MapScreenViewModel


@Composable
fun VehiclesFilterScreen(
    mapScreenViewModel: MapScreenViewModel,
    vehiclesFilterScreenViewModel: VehiclesFilterScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val tramButtonColor: ButtonColors =
        if (vehiclesFilterScreenViewModel.uiState.collectAsState().value.isBusSelectionShown) ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ) else ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
    val busButtonColor: ButtonColors =
        if (!vehiclesFilterScreenViewModel.uiState.collectAsState().value.isBusSelectionShown) ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ) else ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)

    val tramButtonTextColor: Color =
        if (vehiclesFilterScreenViewModel.uiState.collectAsState().value.isBusSelectionShown) Color.Black else Color.White
    val busButtonTextColor: Color =
        if (!vehiclesFilterScreenViewModel.uiState.collectAsState().value.isBusSelectionShown) Color.Black else Color.White

    val chosenTramLines = mapScreenViewModel.uiState.collectAsState().value.chosenTramLines
    val chosenBusLines = mapScreenViewModel.uiState.collectAsState().value.chosenBusLines

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

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                colors = tramButtonColor,
                onClick = { vehiclesFilterScreenViewModel.selectTramSelectionMenu() },
                modifier = Modifier.padding(start = 48.dp, end = 16.dp)
            ) {
                Text(text = "Tramwaje", color = tramButtonTextColor)
            }
            Button(
                onClick = { vehiclesFilterScreenViewModel.selectBusSelectionMenu() },
                colors = busButtonColor,
                modifier = Modifier.padding(start = 16.dp, end = 48.dp)
            ) {
                Text(text = "Autobusy", color = busButtonTextColor)
            }
        }
        FlowRow {
            if (vehiclesFilterScreenViewModel.uiState.collectAsState().value.isBusSelectionShown) {
                vehiclesFilterScreenViewModel.getAllBusLines().forEach {
                    if (chosenBusLines.contains(it)) {
                        Button(
                            onClick = { mapScreenViewModel.removeBusLine(it) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(text = it, color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = { mapScreenViewModel.addBusLine(it) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            Text(text = it, color = Color.Black)
                        }
                    }
                }

            } else {
                vehiclesFilterScreenViewModel.getAllTramLines().forEach {
                    if (chosenTramLines.contains(it)) {
                        Button(
                            onClick = { mapScreenViewModel.removeTramLine(it) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(text = it, color = Color.White)
                        }
                    } else {
                        Button(
                            onClick = { mapScreenViewModel.addTramLine(it) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                        ) {
                            Text(text = it, color = Color.Black)
                        }
                    }
                }
            }
        }

    }
}