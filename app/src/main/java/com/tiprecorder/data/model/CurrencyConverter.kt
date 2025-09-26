package com.tiprecorder.data.model

import java.util.*

object CurrencyConverter {

    private val exchangeRates = mapOf(
        "TRY" to 1.0,
        "USD" to 41.3936,
        "EUR" to 48.683,
        "JPY" to 0.2816,
        "CAD" to 29.81,
        "AUD" to 27.19,
        "CHF" to 52.33,
        "CNY" to 5.8149,
        "SEK" to 4.2,
        "NOK" to 4.0,
        "DKK" to 7.0,
        "PLN" to 10.0,
        "RUB" to 0.5,
        "INR" to 0.55,
        "BRL" to 8.0,
        "KRW" to 0.032,
        "MXN" to 2.0,
        "SGD" to 28.0,
        "HKD" to 5.0
    )

    fun convertToTRY(amount: Double, currency: String): Double {
        val rate = exchangeRates[currency] ?: 1.0
        return amount * rate
    }

    fun getExchangeRate(currency: String): Double {
        return exchangeRates[currency] ?: 1.0
    }

    fun getSupportedCurrencies(): Set<String> {
        return exchangeRates.keys
    }

    fun isCurrencySupported(currency: String): Boolean {
        return exchangeRates.containsKey(currency)
    }
}
