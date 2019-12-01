package currencyconverter.usecases

import currencyconverter.domain.ExchangeRate
import currencyconverter.repo.ExchangeRepo
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrenciesListUseCase @Inject constructor (val exchangeRepo: ExchangeRepo)
    : ObservableUseCase<MutableList<ExchangeRate>, GetCurrenciesListUseCase.Params>(){
    override fun buildObservableUseCase(params: Params): Observable<MutableList<ExchangeRate>> {
        return params.let {  exchangeRepo.getCurrenciesRates(it.base) }
    }

    class Params(val base : String)
}