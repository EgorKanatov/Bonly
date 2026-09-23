package com.example.bonly.presentation

import androidx.lifecycle.ViewModel
import com.example.bonly.domain.Bond
import com.example.bonly.domain.CalculateNetYieldUseCase
import com.example.bonly.domain.calculateDaysBetween
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val netYieldUseCase: CalculateNetYieldUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    init {
        recalculate()
    }

    private fun recalculate() {
        val bond = Bond(
            "",
            _state.value.nominal.replace(',', '.').toDoubleOrNull() ?: 0.0,
            _state.value.pricePercent.replace(',', '.').toDoubleOrNull() ?: 0.0,
            _state.value.coupon.replace(',', '.').toDoubleOrNull() ?: 0.0,
            _state.value.nkd.replace(',', '.').toDoubleOrNull() ?: 0.0,
            _state.value.couponsPerYear.toIntOrNull() ?: 1,
            _state.value.daysToMaturity.toIntOrNull() ?: 1,
        )

        val result = netYieldUseCase.calculateYield(bond)
        val couponPerYear = netYieldUseCase.totalGrossCoupons()
        _state.update { currentState ->
            currentState.copy(yieldResult = result, couponSum = couponPerYear)
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


    fun onNominalChanged(value: String) {
        _state.update { it.copy(nominal = value) }
        recalculate()
    }

    fun onPercentChanged(value: String) {
        _state.update { it.copy(pricePercent = value) }
        recalculate()
    }

    fun onCouponChanged(value: String) {
        _state.update { it.copy(coupon = value) }
        recalculate()
    }

    fun onNkdChanged(value: String) {
        _state.update { it.copy(nkd = value) }
        recalculate()
    }

    fun onCouponsPerYearChanged(value: String) {
        _state.update { it.copy(couponsPerYear = value) }
        recalculate()
    }

    fun onDaysToMaturityChanged(value: String) {
        _state.update { it.copy(daysToMaturity = value) }
        recalculate()
    }

}
