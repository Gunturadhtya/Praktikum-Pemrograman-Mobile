package com.mobil.modul2xml

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.mobil.modul2xml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val controller = TipCalculatorController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()
        setupListeners()
        updateTipDisplay()
    }

    private fun setupDropdown() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            controller.tipOptions.map { "$it%" }
        )
        binding.actvTipPercentage.setAdapter(adapter)

        binding.actvTipPercentage.setText("${controller.selectedPercentage}%", false)
    }

    private fun setupListeners() {
        binding.etBillAmount.doAfterTextChanged { text ->
            controller.updateBillAmount(text.toString())
            updateTipDisplay()
        }

        binding.actvTipPercentage.setOnItemClickListener { _, _, position, _ ->
            controller.updatePercentage(controller.tipOptions[position])
            updateTipDisplay()
        }

        binding.switchRoundUp.setOnCheckedChangeListener { _, isChecked ->
            controller.toggleRoundUp(isChecked)
            updateTipDisplay()
        }
    }

    private fun updateTipDisplay() {
        binding.tvTipAmount.text = getString(R.string.tip_amount_result, controller.getTipAmount())
    }
}


