package com.example.mpkAndroid.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mpkAndroid.ui.theme.MpkAndroidTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen(){
    val wroclaw = LatLng(51.11, 17.04)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(wroclaw, 13f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

    }
}


@Composable
@Preview
fun MapScreenPreview() {
    MpkAndroidTheme {
        MapScreen()
    }
}