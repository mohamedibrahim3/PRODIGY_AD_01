package com.mada.calculatorapp.calculator

data class CalculatorState(
    val num1: String = "",
    val num2: String = "",
    val operation: CalculatorOperation? = null
)
