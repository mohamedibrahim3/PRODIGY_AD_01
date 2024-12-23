package com.mada.calculatorapp.common

import androidx.compose.ui.graphics.Color
import com.mada.calculatorapp.ui.theme.Orange

val buttonRows = listOf(
    listOf(
        "AC" to Color.DarkGray,
        "Del" to Color.DarkGray,
        "%" to Orange,
        "÷" to Orange
    ),
    listOf(
        "7" to Color.DarkGray,
        "8" to Color.DarkGray,
        "9" to Color.DarkGray,
        "×" to Orange
    ),
    listOf(
        "4" to Color.DarkGray,
        "5" to Color.DarkGray,
        "6" to Color.DarkGray,
        "-" to Orange
    ),
    listOf(
        "1" to Color.DarkGray,
        "2" to Color.DarkGray,
        "3" to Color.DarkGray,
        "+" to Orange
    ),
    listOf("0" to Color.DarkGray, "." to Color.DarkGray, "=" to Orange)
)
