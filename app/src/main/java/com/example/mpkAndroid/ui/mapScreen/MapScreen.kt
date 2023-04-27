package com.example.mpkAndroid.ui.mapScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mpkAndroid.domain.model.VehicleType
import com.example.mpkAndroid.ui.MapMarker
import com.example.mpkAndroid.ui.MapMarkerType
import com.example.mpkAndroid.ui.theme.MpkAndroidTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    mapScreenViewModel : MapScreenViewModel = hiltViewModel<MapScreenViewModel>(),
    navController : NavController
) {
    val cameraPositionState = rememberCameraPositionState {
        position = mapScreenViewModel.uiState.value.startingCameraPosition!!
    }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = { navController.navigate("vehiclesFilter") },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Linie")
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            mapScreenViewModel.uiState.collectAsState().value.vehiclesPositions.forEach { vehicle ->
                when (vehicle.type) {
                    VehicleType.BUS -> MapMarker(
                        position = LatLng(vehicle.latitude, vehicle.longitude),
                        title = vehicle.number,
                        type = MapMarkerType.BUS
                    )
                    VehicleType.TRAM -> MapMarker(
                        position = LatLng(vehicle.latitude, vehicle.longitude),
                        title = vehicle.number,
                        type = MapMarkerType.TRAM
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun MapScreenPreview() {
    MpkAndroidTheme {
        MapScreen(navController = rememberNavController())
    }
}