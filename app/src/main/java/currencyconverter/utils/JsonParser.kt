package currencyconverter.utils

import currencyconverter.domain.Constants
import org.json.JSONException
import org.json.JSONObject
import currencyconverter.domain.ExchangeRate

class JsonParser{

    companion object {
        fun parseExchangeRates(key: String, json: String?): MutableList<ExchangeRate>? {
            val exchangeList = mutableListOf<ExchangeRate>()

            if (json.isNullOrEmpty()) {
                return null
            }

            try {
                val base = JSONObject(json).getString(Constants.BASE)
                exchangeList.add(ExchangeRate(base, 1.0))

                val rateObj = JSONObject(json).getJSONObject(key)
                val keys = rateObj.keys()
                while (keys.hasNext()) {
                    val currency = keys.next()
                    val value = rateObj.getDouble(currency)

                    val exchangeRate = ExchangeRate(currency, value);
                    exchangeList.add(exchangeRate)
                }
            } catch (e: JSONException) {
                return null
            }

            return exchangeList
        }
    }
}