package com.mobil.modul2xml

import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode

object TipCalculatorModel {
    fun calculateTip(
        amount: BigDecimal,
        percentage: BigDecimal,
        isRoundUp: Boolean
    ): BigDecimal {
        val calculatedTip = amount.multiply(percentage)
        return if (isRoundUp) {
            calculatedTip.setScale(0, RoundingMode.UP).setScale(2)
        } else {
            calculatedTip.setScale(2, RoundingMode.HALF_EVEN)
        }
    }
}