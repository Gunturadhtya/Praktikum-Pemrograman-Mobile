package com.mobil.modul2xml

import java.math.BigDecimal

class TipCalculatorController {

    val tipOptions: List<String> = listOf("15", "18", "20")

    var billAmountInput: String = ""
    var selectedPercentage: String = "15"
    var isRoundUp: Boolean = true

    fun getTipAmount(): String {
        val amount = billAmountInput.toBigDecimalOrNull() ?: BigDecimal.ZERO
        val percentage = selectedPercentage.toBigDecimal().divide(BigDecimal(100))
        val tip = TipCalculatorModel.calculateTip(amount, percentage, isRoundUp)
        return tip.toString()
    }

    fun updateBillAmount(input: String) {
        billAmountInput = input
    }

    fun updatePercentage(newPercent: String) {
        selectedPercentage = newPercent
    }

    fun toggleRoundUp(enabled: Boolean) {
        isRoundUp = enabled
    }
}


