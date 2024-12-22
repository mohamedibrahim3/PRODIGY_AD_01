package com.mada.calculatorapp.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mada.calculatorapp.calculator.CalculatorState

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set
}