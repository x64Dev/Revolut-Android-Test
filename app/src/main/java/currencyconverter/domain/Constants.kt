package currencyconverter.domain

class Constants {
    companion object{
        const val CURRENCY_EXCHANGE_BASE_URL = "https://revolut.duckdns.org";
        const val API_LATEST_RATES = "/latest"
        const val BASE = "base"
        const val RATES = "rates"
        var  defaultBase = "EUR"
    }
}