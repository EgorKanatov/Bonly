package com.example.bonly.presentation

import com.example.bonly.data.BondReportEntity
import com.example.bonly.domain.DatabaseUseCases
import com.example.bonly.presentation.reports.ReportsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val databaseUseCases: DatabaseUseCases
) : ViewModel() {

    val state: StateFlow<ReportsState> = databaseUseCases.getAll()
        .map { reports ->
            ReportsState(
                isLoading = false,
                reports = reports
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ReportsState(isLoading = true)
        )

    fun deleteReport(report: BondReportEntity) {
        viewModelScope.launch {
            databaseUseCases.delete(report)
        }
    }
}
