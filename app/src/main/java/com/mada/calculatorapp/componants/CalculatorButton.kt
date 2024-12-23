package com.mada.calculatorapp.componants

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val fontSize = if (symbol == "AC" || symbol == "Del") 18.sp else 28.sp

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
            fontSize = fontSize,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
