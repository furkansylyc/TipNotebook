package com.tiprecorder.data.model

data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)

object Currencies {
    val supportedCurrencies = listOf(
        Currency("TRY", "Türk Lirası", "₺"),
        Currency("USD", "Amerikan Doları", "$"),
        Currency("EUR", "Euro", "€"),
        Currency("GBP", "İngiliz Sterlini", "£"),
        Currency("JPY", "Japon Yeni", "¥"),
        Currency("CAD", "Kanada Doları", "C$"),
        Currency("AUD", "Avustralya Doları", "A$"),
        Currency("CHF", "İsviçre Frangı", "CHF"),
        Currency("CNY", "Çin Yuanı", "¥"),
        Currency("SEK", "İsveç Kronu", "kr"),
        Currency("NOK", "Norveç Kronu", "kr"),
        Currency("DKK", "Danimarka Kronu", "kr"),
        Currency("PLN", "Polonya Zlotisi", "zł"),
        Currency("RUB", "Rus Rublesi", "₽"),
        Currency("INR", "Hindistan Rupisi", "₹"),
        Currency("BRL", "Brezilya Reali", "R$"),
        Currency("KRW", "Güney Kore Wonu", "₩"),
        Currency("MXN", "Meksika Pesosu", "$"),
        Currency("SGD", "Singapur Doları", "S$"),
        Currency("HKD", "Hong Kong Doları", "HK$")
    )
}
