package com.example.mpkAndroid.ui.reportScreens.newReportScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mpkAndroid.domain.model.ReportType

@Composable
fun ReportTypeDropdown(
    modifier: Modifier = Modifier,
    initialSelection: ReportType? = null,
    onSelectionChange: (ReportType) -> Unit
) {
    val expandedState = remember { mutableStateOf(false) }
    val selectedReportType = remember { mutableStateOf(initialSelection) }

    val arrowIcon = Icons.Default.ArrowDropDown

    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { expandedState.value = true }
                .background(Color.LightGray)
                .padding(17.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = selectedReportType.value?.translation ?: "",
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Box(
            modifier = Modifier
                .wrapContentWidth(align = Alignment.End)
                .clickable { expandedState.value = true }
                .background(Color.LightGray)
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = arrowIcon,
                contentDescription = "",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

    DropdownMenu(
        expanded = expandedState.value,
        onDismissRequest = { expandedState.value = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        ReportType.values().forEach { reportType ->
            DropdownMenuItem(
                onClick = {
                    selectedReportType.value = reportType
                    expandedState.value = false
                    onSelectionChange(reportType)
                }
            ) {
                Text(
                    text = reportType.translation,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}