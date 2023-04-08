package com.example.mpkAndroid.ui.mapScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mpkAndroid.domain.model.VehicleType
import com.example.mpkAndroid.ui.MapMarker
import com.example.mpkAndroid.ui.MapMarkerType
import com.example.mpkAndroid.ui.theme.MpkAndroidTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(
    mapScreenViewModel : MapScreenViewModel = hiltViewModel<MapScreenViewModel>()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = mapScreenViewModel.uiState.value.startingCameraPosition!!
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


@Composable
@Preview
fun MapScreenPreview() {
    MpkAndroidTheme {
        MapScreen()
    }
}