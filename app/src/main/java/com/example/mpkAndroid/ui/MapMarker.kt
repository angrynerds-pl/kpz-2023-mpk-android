package com.example.mpkAndroid.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.mpkAndroid.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapMarker(
    position: LatLng,
    title: String,
    type: MapMarkerType = MapMarkerType.BUS,
    iconSize: Int = 100
) {
    val icon = bitmapDescriptorFromVector(LocalContext.current, type.iconId, iconSize)
    Marker(
        state = MarkerState(position),
        title = title,
        icon = icon
    )
}

enum class MapMarkerType(@DrawableRes val iconId: Int) {
    BUS(R.drawable.bus),
    TRAM(R.drawable.tram_mock)
}

fun bitmapDescriptorFromVector(
    context: Context,
    @DrawableRes vectorDrawableResourceId: Int,
    bitmapSize: Int
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context, vectorDrawableResourceId)
    vectorDrawable!!.setBounds(0, 0, bitmapSize, bitmapSize)

    val bitmap = Bitmap.createBitmap(
        bitmapSize,
        bitmapSize,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}