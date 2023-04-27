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
    var startingCameraPosition: CameraPosition? = null,
    val vehiclesPositions: List<Vehicle> = emptyList(),
    var chosenTramLines: MutableSet<String> = mutableSetOf("8"),
    var chosenBusLines: MutableSet<String> = mutableSetOf("145")

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
        }, 0, 30, TimeUnit.SECONDS)
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
        _uiState.value.startingCameraPosition = mapPositionsUseCase.getCameraStartingPosition()
    }

    fun removeBusLine(line: String) {

        _uiState.update { currentState ->
            val newChosenBusLines = currentState.chosenBusLines
            newChosenBusLines.remove(line)
            currentState.copy(
                chosenBusLines = newChosenBusLines
            )

        }
    }

    fun addBusLine(line: String) {
        _uiState.update { currentState ->
            val newChosenBusLines = currentState.chosenBusLines
            newChosenBusLines.add(line)
            currentState.copy(
                chosenBusLines = newChosenBusLines
            )

        }
    }

    fun removeTramLine(line: String) {
        _uiState.update { currentState ->
            val newChosenTramLines = currentState.chosenTramLines
            newChosenTramLines.remove(line)
            currentState.copy(
                chosenTramLines = newChosenTramLines
            )

        }
    }

    fun addTramLine(line: String) {
        _uiState.update { currentState ->
            val newChosenTramLines = currentState.chosenTramLines
            newChosenTramLines.add(line)
            currentState.copy(
                chosenTramLines = newChosenTramLines
            )

        }
    }

    override fun onCleared() {
        backgroundExecutor.shutdownNow()
    }
}