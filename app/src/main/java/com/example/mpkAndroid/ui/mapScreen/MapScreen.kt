package com.example.mpkAndroid.ui.mapScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mpkAndroid.ui.MapMarker
import com.example.mpkAndroid.ui.MapMarkerType
import com.example.mpkAndroid.ui.theme.MpkAndroidTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun MapScreen(
    mapScreenViewModel : MapScreenViewModel = hiltViewModel<MapScreenViewModel>()
) {
    val wroclaw = LatLng(51.11, 17.04)
    val cameraPositionState = rememberCameraPositionState {
        position = mapScreenViewModel.uiState.value.startingCameraPosition!!
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        MapMarker(
            position = wroclaw,
            title = "16",
            type = MapMarkerType.BUS
        )
    }
}


@Composable
@Preview
fun MapScreenPreview() {
    MpkAndroidTheme {
        MapScreen()
    }
}