package com.mada.calculatorapp.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.calculatorapp.ui.theme.MediumGray
import com.mada.calculatorapp.ui.theme.Orange

@Composable
fun CalculatorScreen(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    Box(modifier = modifier.fillMaxSize().background(MediumGray)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = state.num1 + (state.operation?.symbol ?: "") + state.num2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2
            )

            val buttonRows = listOf(
                listOf("AC" to Color.LightGray, "Del" to Color.LightGray, "/" to Color.LightGray),
                listOf("7" to Color.DarkGray, "8" to Color.DarkGray, "9" to Color.DarkGray, "*" to Orange),
                listOf("4" to Color.DarkGray, "5" to Color.DarkGray, "6" to Color.DarkGray, "-" to Orange),
                listOf("1" to Color.DarkGray, "2" to Color.DarkGray, "3" to Color.DarkGray, "+" to Orange),
                listOf("0" to Color.DarkGray, "." to Color.DarkGray, "=" to Orange)
            )

            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { (symbol, color) ->
                        CalculatorButton(
                            symbol = symbol,
                            color = color,
                            modifier = if (symbol == "0") Modifier.weight(2f) else Modifier.weight(1f),
                            onClick = {
                                when (symbol) {
                                    "AC" -> onAction(CalculatorAction.Clear)
                                    "Del" -> onAction(CalculatorAction.Delete)
                                    "/" -> onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                                    "*" -> onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                                    "-" -> onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                                    "+" -> onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                                    "=" -> onAction(CalculatorAction.Calculate)
                                    "." -> onAction(CalculatorAction.Decimal)
                                    else -> onAction(CalculatorAction.Number(symbol.toIntOrNull() ?: 0))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    symbol: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .aspectRatio(if (symbol == "0") 2f else 1f)
            .height(64.dp)
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
