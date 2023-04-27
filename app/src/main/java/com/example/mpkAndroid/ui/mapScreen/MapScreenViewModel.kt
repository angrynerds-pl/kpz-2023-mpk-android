package com.example.mpkAndroid.ui.mapScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.example.mpkAndroid.domain.model.Vehicle
import com.google.android.gms.maps.model.CameraPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class MapScreenState(
    val startingCameraPosition: CameraPosition? = null,
    val vehiclesPositions: List<Vehicle> = emptyList(),
    var chosenTramLines: Set<String> = setOf("8"),
    var chosenBusLines: Set<String> = setOf("145")

)

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val mapPositionsUseCase: MapPositionsUseCase,
    private val backgroundExecutor: ScheduledExecutorService
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapScreenState())
    val uiState: StateFlow<MapScreenState> = _uiState.asStateFlow()

    init {
        getStartingPositionOfCamera()
        backgroundExecutor.scheduleAtFixedRate({
            updateVehiclesPosition()
        }, 0, 15, TimeUnit.SECONDS)
    }

    //remove default chosen lines when implementing lines choosing
    fun updateVehiclesPosition(
    ) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    vehiclesPositions = mapPositionsUseCase.getVehiclesPositions(
                        currentState.chosenTramLines,
                        currentState.chosenBusLines
                    ) as List<Vehicle>
                )
            }
        }
    }

    private fun getStartingPositionOfCamera() {
        _uiState.value = MapScreenState(mapPositionsUseCase.getCameraStartingPosition())
    }

    override fun onCleared() {
        backgroundExecutor.shutdownNow()
    }
}