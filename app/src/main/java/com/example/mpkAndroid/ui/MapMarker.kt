package com.example.mpkAndroid.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.mpkAndroid.R
import com.example.mpkAndroid.ui.theme.BusTagColor
import com.example.mpkAndroid.ui.theme.TramTagColor
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
    val icon = bitmapDescriptorFromVector(
        LocalContext.current, type, iconSize, title
    )
    Marker(
        state = MarkerState(position),
        title = title,
        icon = icon
    )
}

enum class MapMarkerType(@DrawableRes val iconId: Int, val color: Int) {
    BUS(R.drawable.bus, BusTagColor.toArgb()),
    TRAM(R.drawable.tram, TramTagColor.toArgb())
}

fun bitmapDescriptorFromVector(
    context: Context,
    vehicleType: MapMarkerType,
    bitmapSize: Int,
    text: String = ""
): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(context, vehicleType.iconId)
    vectorDrawable!!.setBounds(0, 0, bitmapSize, bitmapSize)
    val bitmap = Bitmap.createBitmap(
        bitmapSize,
        bitmapSize,
        Bitmap.Config.ARGB_8888
    )
    val paint = Paint().apply {
        color = vehicleType.color
        textSize = 28f
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    canvas.drawText(
        text,
        bitmapSize / 2f,
        bitmapSize / 2.25f,
        paint
    )

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}