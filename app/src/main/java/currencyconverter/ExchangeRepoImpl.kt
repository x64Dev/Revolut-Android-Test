package currencyconverter

import currencyconverter.utils.JsonParser
import currencyconverter.domain.Constants
import currencyconverter.domain.ExchangeRate
import currencyconverter.net.ExchangeService
import currencyconverter.repo.ExchangeRepo
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class ExchangeRepoImpl (private val retrofit: Retrofit ) : ExchangeRepo {
    override fun getCurrenciesRates(base : String): Observable<MutableList<ExchangeRate>> {
        val exchangeService = retrofit.create(ExchangeService::class.java);

        return exchangeService.getRates(base).map {
            JsonParser.parseExchangeRates(Constants.RATES, it)
        }
    }

    override fun updateCurrenciesRate(baseRate: Double, list: MutableList<ExchangeRate>)
            : Observable<MutableList<ExchangeRate>> {
        for(exchangeRate in ArrayList<ExchangeRate>(list)){
            exchangeRate.mRate.let { exchangeRate.mRate = it * baseRate }
        }

        return Observable.just(list).debounce (1000, TimeUnit.MILLISECONDS)
    }
}