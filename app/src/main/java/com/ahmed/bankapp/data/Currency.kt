package com.ahmed.bankapp.data

data class Currency(
    private val countryName: String,
    private val currencyCode: String,
    private val currencyName: String,
    val rate: Float
) {

    companion object {

        private var currencies = ArrayList<Currency>()
        private fun getEmptyCurrency(): Currency {
            return Currency("", "", "", 0.0f)
        }

        private fun toUpperCase(word: String): String {
            var newWord = ""

            for (x in word.indices) {
                // Convert letter to Capital if it's small.
                if (word[x].code > 90) {
                    newWord += Char(word[x].code - 32)
                } else {
                    // Otherwise do not convert.
                    newWord += word[x]
                }
            }
            return newWord
        }


        fun getCurrencies(): ArrayList<Currency> {
            return currencies
        }

        fun findByCode(currencyCode: String): Currency {
            for (x in currencies.indices) {
                if (toUpperCase(currencyCode) == currencies[x].currencyCode) {
                    return Currency(
                        currencies[x].countryName,
                        currencies[x].currencyCode,
                        currencies[x].currencyName,
                        currencies[x].rate
                    )
                }
            }
            // No currency has been found
            return getEmptyCurrency()
        }

        fun findByCountry(country: String): Currency {
            for (x in currencies.indices) {
                if (toUpperCase(country) == toUpperCase(currencies[x].countryName)) {
                    return Currency(
                        currencies[x].countryName,
                        currencies[x].currencyCode,
                        currencies[x].currencyName,
                        currencies[x].rate
                    )
                }
            }
            // No Country has been found
            return getEmptyCurrency()
        }
    }


    fun updateRate(
        currencyCode: String,
        rate: Float
    ): Currency {
        for (i in currencies.indices) {
            if (toUpperCase(currencyCode) == currencies[i].currencyCode) {
                currencies[i].apply {
                    val item = Currency(
                        countryName,
                        currencyCode,
                        currencyName,
                        rate
                    )
                    currencies.set(i, item)
                    return item
                }
            }
        }
        return getEmptyCurrency()
    }


    fun isCurrencyExist(): Boolean{
        return currencyCode != ""
    }

    fun countryName(): String {
        return countryName
    }

    fun currencyCode(): String {
        return currencyCode
    }

    fun currencyName(): String {
        return currencyName
    }
}
