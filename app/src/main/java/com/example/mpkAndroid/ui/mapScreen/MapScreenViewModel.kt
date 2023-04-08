package com.example.mpkAndroid.ui.mapScreen

import androidx.lifecycle.ViewModel
import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.example.mpkAndroid.domain.model.Vehicle
import com.google.android.gms.maps.model.CameraPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MapScreenState(
    val startingCameraPosition: CameraPosition? = null,
    val vehiclesPositions: List<Vehicle> = emptyList()
)

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val mapPositionsUseCase : MapPositionsUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(MapScreenState())
    val uiState: StateFlow<MapScreenState> = _uiState.asStateFlow()

    init{
        getStartingPositionOfCamera()
        updateVehiclesPosition()
    }

    //remove default chosen lines when implementing lines choosing
    fun updateVehiclesPosition(chosenLines: Set<String> = setOf("145", "8")){
        _uiState.update { currentState ->
            currentState.copy(
                vehiclesPositions = mapPositionsUseCase.getVehiclesPositions(chosenLines)
            )
        }
    }

    private fun getStartingPositionOfCamera(){
        _uiState.value = MapScreenState(mapPositionsUseCase.getCameraStartingPosition())
    }
}