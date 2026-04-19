package com.example.modul2

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.math.BigDecimal

class TipCalculatorController(
    val billAmountState: TextFieldState = TextFieldState()
) {
    var selectedPercentage by mutableStateOf("15")
    var isRoundUp by mutableStateOf(true)

    val tipAmount by derivedStateOf {
        val amount = billAmountState.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
        val percentage = selectedPercentage.toBigDecimal().divide(BigDecimal(100))

        TipCalculatorModel.calculateTip(amount, percentage, isRoundUp)
    }

    fun updatePercentage(newPercent: String) {
        selectedPercentage = newPercent
    }

    fun toggleRoundUp(enabled: Boolean) {
        isRoundUp = enabled
    }
}