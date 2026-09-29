package com.example.bonly.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonly.data.BondReportEntity
import com.example.bonly.domain.Bond
import com.example.bonly.domain.CalculateNetYieldUseCase
import com.example.bonly.domain.DatabaseUseCases
import com.example.bonly.domain.calculateDaysBetween
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val netYieldUseCase: CalculateNetYieldUseCase,
    private val databaseUseCases: DatabaseUseCases,
) : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    init {
        recalculate()
    }

    private fun recalculate() {
        val bond = Bond(
            name = _state.value.name,
            nominal = _state.value.nominal.toDoubleOrNull() ?: 0.0,
            pricePercent = _state.value.pricePercent.toDoubleOrNull() ?: 0.0,
            coupon = _state.value.coupon.toDoubleOrNull() ?: 0.0,
            nkd = _state.value.nkd.toDoubleOrNull() ?: 0.0,
            couponsPerYear = _state.value.couponsPerYear.toIntOrNull() ?: 1,
            daysToMaturity = _state.value.daysToMaturity.toIntOrNull() ?: 1
        )

        val result = netYieldUseCase.calculateYield(bond)

        _state.update { currentState ->
            currentState.copy(
                yieldResult = result.netYield,
                couponSum = result.totalCoupons
            )
        }
    }


    fun saveReport() {
        viewModelScope.launch {
            val report = BondReportEntity(
                name = state.value.name.ifBlank { "Без названия" },
                currentPrice = (state.value.nominal.toDoubleOrNull()
                    ?: 0.0) * (state.value.pricePercent.toDoubleOrNull() ?: 0.0) / 100,
                coupon = state.value.coupon.toDoubleOrNull() ?: 0.0,
                netYield = state.value.yieldResult,
                totalCoupons = state.value.couponSum,
            )
            databaseUseCases.save(report)
        }
    }


    fun onNameChanged(name: String) {
        _state.update { currentState ->
            currentState.copy(name = name)
        }
        recalculate()
    }

    fun onDatesSelected(buyDateMillis: Long, maturityDateMillis: Long) {
        val calculatedDays = calculateDaysBetween(buyDateMillis, maturityDateMillis)

        _state.update { currentState ->
            currentState.copy(daysToMaturity = calculatedDays.toString())
        }
        recalculate()
    }

    fun onNominalChanged(input: String) {
        val formatted = formatNumberInput(input)
        _state.update { it.copy(nominal = formatted) }
        recalculate()
    }

    fun onPercentChanged(input: String) {
        val formatted = formatNumberInput(input)
        _state.update { it.copy(pricePercent = formatted) }
        recalculate()
    }

    fun onCouponChanged(input: String) {
        val formatted = formatNumberInput(input)
        _state.update { it.copy(coupon = formatted) }
        recalculate()
    }

    fun onNkdChanged(input: String) {
        val formatted = formatNumberInput(input)
        _state.update { it.copy(nkd = formatted) }
        recalculate()
    }

    fun onCouponsPerYearChanged(value: String) {
        _state.update { it.copy(couponsPerYear = value) }
        recalculate()
    }

    fun onDaysToMaturityChanged(input: String) {
        val cleanDigits = input.filter { it.isDigit() }
        val formatted = if (cleanDigits.length > 1 && cleanDigits.startsWith("0")) {
            cleanDigits.dropWhile { it == '0' }.ifEmpty { "0" }
        } else {
            cleanDigits
        }
        _state.update { it.copy(daysToMaturity = formatted) }
        recalculate()
    }

}
private fun formatNumberInput(input: String): String {
    val normalized = input.replace(',', '.')

    if (normalized.isEmpty() || normalized == ".") return normalized

    return if (normalized.length > 1 && normalized.startsWith("0") && !normalized.startsWith("0.")) {
        normalized.dropWhile { it == '0' }.ifEmpty { "0" }
    } else {
        normalized
    }
}
