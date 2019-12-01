package currencyconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import currencyconverter.usecases.GetCurrenciesListUseCase
import currencyconverter.usecases.UpdateCurrenciesRateUseCase
import currencyconverter.viewmodels.MainVM
import javax.inject.Inject

class MainVMFactory  @Inject constructor(
    private val getCurrenciesListUseCase: GetCurrenciesListUseCase,
    private val updateCurrenciesRateUseCase: UpdateCurrenciesRateUseCase
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainVM::class.java)) {
            return MainVM(getCurrenciesListUseCase, updateCurrenciesRateUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}