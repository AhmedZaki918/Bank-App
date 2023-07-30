package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.databinding.ActivityCurrencyExchangeBinding
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class CurrencyExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyExchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityCurrencyExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Currency Exchange")
    }
}