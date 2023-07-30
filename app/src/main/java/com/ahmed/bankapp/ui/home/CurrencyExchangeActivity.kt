package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.databinding.ActivityCurrencyExchangeBinding
import com.ahmed.bankapp.ui.currency.CurrenciesActivity
import com.ahmed.bankapp.ui.currency.SearchCurrencyActivity
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity

class CurrencyExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyExchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityCurrencyExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader("Currency Exchange")
            cvShowCurrencies.click {
                openActivity(CurrenciesActivity::class.java)
            }
            cvSearchCurrency.click {
                openActivity(SearchCurrencyActivity::class.java)
            }
        }
    }
}