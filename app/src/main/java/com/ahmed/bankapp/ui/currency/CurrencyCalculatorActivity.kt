package com.ahmed.bankapp.ui.currency

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.Constants.THREE_DIGITS
import com.ahmed.bankapp.data.Constants.USD
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ActivityCurrencyCaluclatorBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity
import com.ahmed.bankapp.util.toast

class CurrencyCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyCaluclatorBinding
    private var dollarAmount = 0.0
    private var sourceAmount = 0.0
    private var sourceCurrencyCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityCurrencyCaluclatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(
                getString(R.string.currency_calculator),
                this@CurrencyCalculatorActivity
            )
            btnConvert.click {
                convertFromCurrency()
            }

            btnClear.click {
                // Restart the activity
                finish()
                openActivity(CurrencyCalculatorActivity::class.java)
            }
        }
    }


    private fun convertFromCurrency() {
        binding.apply {
            val fromCurrency = etFromCurrency.text.toString().trim()
            val toCurrency = etToCurrency.text.toString().trim()
            val amount = etAmount.text.toString().trim()
            val currencyItemOne = Currency.findByCode(fromCurrency)
            val currencyItemTwo = Currency.findByCode(toCurrency)

            if (currencyItemOne.isCurrencyExist() && currencyItemTwo.isCurrencyExist()) {
                etFromCurrency.visibility = INVISIBLE
                etToCurrency.visibility = INVISIBLE
                etAmount.visibility = INVISIBLE
                btnConvert.visibility = INVISIBLE
                btnClear.visibility = VISIBLE
                sourceAmount = amount.toDouble()

                if (currencyItemOne.currencyCode() != USD && currencyItemTwo.currencyCode() != USD) {
                    showCardOneVisibility()
                    showCardTwoVisibility()
                    updateFirstCard(currencyItemOne, amount.toDouble())
                    updateSecondCard(currencyItemTwo)
                } else {
                    showCardOneVisibility()
                    updateFirstCard(currencyItemOne, amount.toDouble())
                }
            } else toast(getString(R.string.invalid_currency_code))
        }
    }


    private fun updateFirstCard(currency: Currency, amount: Double) {
        sourceCurrencyCode = currency.currencyCode()
        binding.apply {
            tvCountry.text = currency.countryName()
            tvCode.text = currency.currencyCode()
            tvCurrencyName.text = currency.currencyName()
            tvExchangeRate.text = currency.rate.toString()

            dollarAmount = currency.convertToDollar(amount)
            val amount3digits = String.format(THREE_DIGITS, dollarAmount).toDouble()

            val convertedAmount = "$amount ${currency.currencyCode()} = $amount3digits $USD"
            tvConvertedAmount.text = convertedAmount
        }
    }

    private fun updateSecondCard(currency: Currency) {
        binding.apply {
            tvToCountry.text = currency.countryName()
            tvToCode.text = currency.currencyCode()
            tvToCurrencyName.text = currency.currencyName()
            tvToExchangeRate.text = currency.rate.toString()

            val amount = currency.convertFromDollar(dollarAmount)
            val amount3digits = String.format(THREE_DIGITS, amount).toDouble()

            val convertedAmount =
                "$sourceAmount $sourceCurrencyCode = $amount3digits ${currency.currencyCode()}"
            tvToConvertedAmount.text = convertedAmount
        }
    }


    private fun showCardOneVisibility() {
        binding.apply {
            ivCountry.visibility = VISIBLE
            tvCountryLabel.visibility = VISIBLE
            tvCountry.visibility = VISIBLE

            ivCode.visibility = VISIBLE
            tvCodeLabel.visibility = VISIBLE
            tvCode.visibility = VISIBLE

            ivCurrency.visibility = VISIBLE
            tvCurrencyLabel.visibility = VISIBLE
            tvCurrencyName.visibility = VISIBLE

            ivExchangeRate.visibility = VISIBLE
            tvExchangeRateLabel.visibility = VISIBLE
            tvExchangeRate.visibility = VISIBLE

            tvConvertedAmount.visibility = VISIBLE
            tvFrom.visibility = VISIBLE
            viewLine.visibility = VISIBLE
        }
    }


    private fun showCardTwoVisibility() {
        binding.apply {
            ivToCountry.visibility = VISIBLE
            tvToCountryLabel.visibility = VISIBLE
            tvToCountry.visibility = VISIBLE

            ivToCode.visibility = VISIBLE
            tvToCodeLabel.visibility = VISIBLE
            tvToCode.visibility = VISIBLE

            ivToCurrency.visibility = VISIBLE
            tvToCurrencyLabel.visibility = VISIBLE
            tvToCurrencyName.visibility = VISIBLE

            ivToExchangeRate.visibility = VISIBLE
            tvToExchangeRateLabel.visibility = VISIBLE
            tvToExchangeRate.visibility = VISIBLE

            tvToConvertedAmount.visibility = VISIBLE
            tvTo.visibility = VISIBLE
        }
    }
}