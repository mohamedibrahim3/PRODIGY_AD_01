package com.mada.calculatorapp.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.calculatorapp.common.buttonRows
import com.mada.calculatorapp.componants.CalculatorButton
import com.mada.calculatorapp.ui.theme.MediumGray

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MediumGray),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(32.dp)) // Adds space at the top

        Text(
            text = state.num1 + (state.operation?.symbol ?: "") + state.num2,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Light,
            fontSize = 80.sp,
            color = Color.White,
            maxLines = 2
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { (symbol, color) ->
                        CalculatorButton(
                            symbol = symbol,
                            color = color,
                            modifier = if (symbol == "0") Modifier.weight(2f) else Modifier.weight(
                                1f
                            ),
                            onClick = {
                                when (symbol) {
                                    "AC" -> onAction(CalculatorAction.Clear)
                                    "Del" -> onAction(CalculatorAction.Delete)
                                    "÷" -> onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                                    "×" -> onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                                    "-" -> onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                                    "+" -> onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                                    "%" -> onAction(CalculatorAction.Operation(CalculatorOperation.Percent))
                                    "=" -> onAction(CalculatorAction.Calculate)
                                    "." -> onAction(CalculatorAction.Decimal)
                                    else -> onAction(
                                        CalculatorAction.Number(
                                            symbol.toIntOrNull() ?: 0
                                        )
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

