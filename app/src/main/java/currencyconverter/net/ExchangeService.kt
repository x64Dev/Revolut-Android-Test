package currencyconverter.net

import currencyconverter.domain.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService{
    @GET(Constants.API_LATEST_RATES)
    fun getRates(@Query(Constants.BASE) base : String) : Observable<String>
}