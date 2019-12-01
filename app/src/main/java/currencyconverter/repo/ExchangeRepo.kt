package currencyconverter.repo

import currencyconverter.domain.ExchangeRate
import io.reactivex.Observable

interface ExchangeRepo {
    fun getCurrenciesRates(base : String) : Observable<MutableList<ExchangeRate>>

    fun updateCurrenciesRate(baseRate : Double, list : MutableList<ExchangeRate>)
            : Observable<MutableList<ExchangeRate>>
}