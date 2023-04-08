package com.example.mpkAndroid.ui.mapScreen

import androidx.lifecycle.ViewModel
import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.google.android.gms.maps.model.CameraPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class MapScreenState(
    val startingCameraPosition: CameraPosition? = null
)

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val mapPositionsUseCase : MapPositionsUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(MapScreenState())
    val uiState: StateFlow<MapScreenState> = _uiState.asStateFlow()

    init{
        getStartingPositionOfCamera()
    }

    fun getStartingPositionOfCamera(){
        _uiState.value = MapScreenState(mapPositionsUseCase.getStartingPosition())
    }
}