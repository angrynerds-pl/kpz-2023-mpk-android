package com.example.mpkAndroid.ui.mapScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpkAndroid.domain.MapPositionsUseCase
import com.example.mpkAndroid.domain.ReportsUseCase
import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.UserCredentials
import com.example.mpkAndroid.domain.model.Vehicle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
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
    val reports: List<Report> = emptyList(),
    val chosenTramLines: Set<String> = setOf("8"),
    val chosenBusLines: Set<String> = setOf("145"),
    val user: UserCredentials? = null,
    val newReportPosition: LatLng? = null,
    val selectedReportMarker: Marker? = null
)

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val mapPositionsUseCase: MapPositionsUseCase,
    private val backgroundExecutor: ScheduledExecutorService,
    private val reportsUseCase: ReportsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MapScreenState())
    val uiState: StateFlow<MapScreenState> = _uiState.asStateFlow()

    init {
        getStartingPositionOfCamera()
        backgroundExecutor.scheduleAtFixedRate({
            updateVehiclesPosition()
        }, 0, 15, TimeUnit.SECONDS)
    }

    fun updateVehiclesPosition() {
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

    fun updateReports() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    reports = reportsUseCase.getReports() as List<Report>
                )
            }
        }
    }

    private fun getStartingPositionOfCamera() {
        _uiState.value.startingCameraPosition = mapPositionsUseCase.getCameraStartingPosition()
    }

    fun removeBusLine(line: String) {

        _uiState.update { currentState ->
            val newChosenBusLines = currentState.chosenBusLines.toMutableSet()
            newChosenBusLines.remove(line)
            currentState.copy(
                chosenBusLines = newChosenBusLines.toSet()
            )

        }
    }

    fun addBusLine(line: String) {
        _uiState.update { currentState ->
            val newChosenBusLines = currentState.chosenBusLines.toMutableSet()
            newChosenBusLines.add(line)
            currentState.copy(
                chosenBusLines = newChosenBusLines.toSet()
            )

        }
    }

    fun removeTramLine(line: String) {
        _uiState.update { currentState ->
            val newChosenTramLines = currentState.chosenTramLines.toMutableSet()
            newChosenTramLines.remove(line)
            currentState.copy(
                chosenTramLines = newChosenTramLines.toSet()
            )

        }
    }

    fun addTramLine(line: String) {
        _uiState.update { currentState ->
            val newChosenTramLines = currentState.chosenTramLines.toMutableSet()
            newChosenTramLines.add(line)
            currentState.copy(
                chosenTramLines = newChosenTramLines.toSet()
            )

        }
    }

    fun updateUser(user: UserCredentials) {
        _uiState.update { currentState ->
            currentState.copy(
                user = user
            )
        }
    }

    fun addNewReport(coordinates: LatLng) {
        _uiState.update { currentState ->
            currentState.copy(
                newReportPosition = coordinates
            )
        }
    }

    fun selectedReport(marker: Marker) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedReportMarker = marker
            )
        }
    }

    fun clearSelectedReport() {
        _uiState.update { currentState ->
            currentState.copy(
                selectedReportMarker = null
            )
        }
    }

    override fun onCleared() {
        backgroundExecutor.shutdownNow()
    }
}