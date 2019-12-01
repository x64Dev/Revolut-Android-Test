package currencyconverter.usecases

import currencyconverter.domain.ExchangeRate
import currencyconverter.repo.ExchangeRepo
import io.reactivex.Observable
import javax.inject.Inject

class UpdateCurrenciesRateUseCase @Inject constructor(val exchangeRepo: ExchangeRepo)
    : ObservableUseCase<MutableList<ExchangeRate>, UpdateCurrenciesRateUseCase.Params>()
{

    override fun buildObservableUseCase(params: Params): Observable<MutableList<ExchangeRate>> {
        params.let {
            return exchangeRepo.updateCurrenciesRate(it.baseRate, it.list)
        }
    }

    class Params(val baseRate : Double, val list : MutableList<ExchangeRate>)
}