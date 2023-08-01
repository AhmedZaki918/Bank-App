package com.ahmed.bankapp.ui.currency

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.Constants.CODE
import com.ahmed.bankapp.data.Constants.COUNTRY
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ActivitySearchCurrencyBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class SearchCurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchCurrencyBinding
    private var searchMode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivitySearchCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(
                getString(R.string.search_currency),
                this@SearchCurrencyActivity
            )
            btnSubmit.click {
                if (searchMode == COUNTRY) etSearch.hint = getString(R.string.country_name)
                switchVisibility()
            }
            btnSearch.click { search() }
            rgSearchMode.setOnCheckedChangeListener { _, id ->
                searchMode = if (id == R.id.rb_code) CODE else COUNTRY
            }
        }
    }


    private fun search() {
        val userInput = binding.etSearch.text.toString().trim()
        if (searchMode == CODE) {
            val currency = Currency.findByCode(userInput)
            updateUi(currency, getString(R.string.invalid_currency_code))
        } else {
            val currency = Currency.findByCountry(userInput)
            updateUi(currency, getString(R.string.invalid_country_name))
        }
    }


    private fun updateUi(currency: Currency, searchError: String) {
        if (currency.isCurrencyExist()) {
            switchVisibilityForCurrency()
            binding.apply {
                tvCountry.text = currency.countryName()
                tvCode.text = currency.currencyCode()
                tvCurrency.text = currency.currencyName()
                tvExchangeRate.text = currency.rate.toString()
            }
        } else toast(searchError)
    }


    private fun switchVisibility() {
        binding.apply {
            ivSearchMode.visibility = INVISIBLE
            tvSearchMode.visibility = INVISIBLE
            rgSearchMode.visibility = INVISIBLE
            btnSubmit.visibility = INVISIBLE
            etSearch.visibility = VISIBLE
            btnSearch.visibility = VISIBLE
        }
    }

    private fun switchVisibilityForCurrency() {
        binding.apply {
            ivCountry.visibility = VISIBLE
            tvCountryLabel.visibility = VISIBLE
            tvCountry.visibility = VISIBLE
            ivCode.visibility = VISIBLE
            tvCodeLabel.visibility = VISIBLE
            tvCode.visibility = VISIBLE
            ivCurrency.visibility = VISIBLE
            tvCurrencyLabel.visibility = VISIBLE
            tvCurrency.visibility = VISIBLE
            ivExchangeRate.visibility = VISIBLE
            tvExchangeRateLabel.visibility = VISIBLE
            tvExchangeRate.visibility = VISIBLE
        }
    }
}