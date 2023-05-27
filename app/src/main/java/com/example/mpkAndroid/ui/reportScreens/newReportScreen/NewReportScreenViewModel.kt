package com.example.mpkAndroid.ui.reportScreens.newReportScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mpkAndroid.domain.ReportsUseCase
import com.example.mpkAndroid.domain.model.Report
import com.example.mpkAndroid.domain.model.ReportType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NewReportScreenState(
    var type: ReportType = ReportType.ACCIDENT
)

@HiltViewModel
class NewReportScreenViewModel @Inject constructor(
    private val reportsUseCase: ReportsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewReportScreenState())
    val uiState: StateFlow<NewReportScreenState> = _uiState.asStateFlow()

    fun updateType(type: ReportType) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    type = type
                )
            }
        }
    }

    fun addNewReport(report: Report) {
        reportsUseCase.addNewReport(report)
    }
}