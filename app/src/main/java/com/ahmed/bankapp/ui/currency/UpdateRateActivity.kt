package com.ahmed.bankapp.ui.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ActivityUpdateRateBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class UpdateRateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateRateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityUpdateRateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Update Rate", this)
        binding.btnUpdate.click {
            updateExchangeRate()
        }
    }

    private fun updateExchangeRate() {
        binding.apply {
            val currencyCode = etCurrencyCode.text.toString().trim()
            val exchangeRate = etExchangeRate.text.toString().trim()

            val currencyItem = Currency.findByCode(currencyCode)
            if (currencyItem.isCurrencyExist()) {
                currencyItem.updateRate(currencyCode, exchangeRate.toFloat())
                toast("Exchange rate has been updated")
                finish()
            } else toast("Invalid currency code")
        }
    }
}