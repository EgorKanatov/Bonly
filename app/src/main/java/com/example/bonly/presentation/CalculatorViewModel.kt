package com.example.bonly.presentation

import androidx.lifecycle.ViewModel
import com.example.bonly.domain.Bond
import com.example.bonly.domain.CalculateNetYieldUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val netYieldUseCase: CalculateNetYieldUseCase
): ViewModel(){
    init {
        val testBond = Bond(
            "Сбер",
            1000.0,
            95.5,
            35.0,
            12.0,
            2,
            365
        )
        val result = netYieldUseCase.calculateYield(testBond)

        println("TEST_YIELD: $result")
    }
}
