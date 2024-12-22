package com.mada.calculatorapp.mvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mada.calculatorapp.calculator.CalculatorAction
import com.mada.calculatorapp.calculator.CalculatorOperation
import com.mada.calculatorapp.calculator.CalculatorState

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action:CalculatorAction){
        when(action) {
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.num1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }

    private fun performDeletion() {
        when{
            state.num2.isNotBlank() -> state = state.copy(
                num2 = state.num2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.num1.isNotBlank() -> state = state.copy(
                num1 = state.num1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.num1.contains(".") && state.num1.isNotBlank()){

            state = state.copy(
                num1 = state.num1 + "."
            )
            return
        }
        if(!state.num2.contains(".") && state.num2.isNotBlank()){

            state = state.copy(
                num2 = state.num2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null){
            if (state.num1.length >= MAX_NUM_LENGHT){
                return
            }
            state = state.copy(
                num1 = state.num1 + number
            )
            return
        }
        if (state.num2.length >= MAX_NUM_LENGHT){
            return
        }
        state = state.copy(
            num2 = state.num2 + number
        )
        return
    }
    companion object{
        private const val MAX_NUM_LENGHT = 8
    }

    private fun performCalculation() {
        val number1 = state.num1.toDoubleOrNull()
        val number2 = state.num2.toDoubleOrNull()
        if(number1 != null && number2 != null){
            val res = when(state.operation){
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Percent -> number1 % number2
                null -> return
            }
            state = state.copy(
                num1 = res.toString().take(15),
                num2 = "",
                operation = null
            )
        }
    }
}