package com.example.mpkAndroid.ui.vehiclesFilterScreen

import androidx.lifecycle.ViewModel
import com.example.mpkAndroid.domain.VehiclesFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class VehiclesFilterScreenState(
    var isBusSelectionShown: Boolean = true
)

@HiltViewModel
class VehiclesFilterScreenViewModel @Inject constructor(
    private val vehiclesFilterUseCase: VehiclesFilterUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(VehiclesFilterScreenState())
    val uiState: StateFlow<VehiclesFilterScreenState> = _uiState.asStateFlow()

    fun selectTramSelectionMenu() {
        _uiState.update { currentState ->
            currentState.copy(
                isBusSelectionShown = false
            )
        }
    }

    fun selectBusSelectionMenu() {
        _uiState.update { currentState ->
            currentState.copy(
                isBusSelectionShown = true
            )
        }
    }

    fun getAllBusLines(): Collection<String> {
        return vehiclesFilterUseCase.getAllBusLines()
    }

    fun getAllTramLines(): Collection<String> {
        return vehiclesFilterUseCase.getAllTramLines()
    }
}