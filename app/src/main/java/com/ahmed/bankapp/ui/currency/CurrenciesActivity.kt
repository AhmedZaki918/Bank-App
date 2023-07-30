package com.ahmed.bankapp.ui.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ActivityCurrenciesBinding
import com.ahmed.bankapp.ui.adapter.CurrenciesAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class CurrenciesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrenciesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityCurrenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader("Currencies (${Currency.getCurrencies().size})")
            rvCurrencies.adapter = CurrenciesAdapter(Currency.getCurrencies())
        }
    }
}