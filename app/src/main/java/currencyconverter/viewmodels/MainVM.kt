package currencyconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import currencyconverter.domain.ExchangeRate
import currencyconverter.usecases.GetCurrenciesListUseCase
import currencyconverter.usecases.UpdateCurrenciesRateUseCase

class MainVM(val getCurrenciesListUseCase: GetCurrenciesListUseCase,
             val updateCurrenciesRateUseCase: UpdateCurrenciesRateUseCase)
    : BaseViewModel(getCurrenciesListUseCase, updateCurrenciesRateUseCase) {

    private val _currenciesLiveData = MutableLiveData<MutableList<ExchangeRate>>()

    val currenciesLiveData : LiveData<MutableList<ExchangeRate>>
    get() = _currenciesLiveData

    private val _progressBarVisibilityLiveData = MutableLiveData<Boolean>()

    val progressBarVisibilityLiveData : MutableLiveData<Boolean>
        get() = _progressBarVisibilityLiveData

    fun getCurrencies(base : String){
        progressBarVisibilityLiveData.value = true

        getCurrenciesListUseCase.execute(
            onSuccess = {
                progressBarVisibilityLiveData.value = false

                _currenciesLiveData.value = it
            },
            onError = { it.printStackTrace() },
            params = GetCurrenciesListUseCase.Params(base)
        )
    }

    fun updateCurrenciesRate(baseRate : Double, list: MutableList<ExchangeRate>){
        updateCurrenciesRateUseCase.execute(
            onSuccess = { _currenciesLiveData.value = it },
            onError = { it.printStackTrace() },
            params = UpdateCurrenciesRateUseCase.Params(baseRate, list)
        )
    }

}