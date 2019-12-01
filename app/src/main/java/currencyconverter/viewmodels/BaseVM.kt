package currencyconverter.viewmodels

import androidx.lifecycle.ViewModel
import currencyconverter.usecases.UseCase

abstract class BaseViewModel(vararg useCases: UseCase) : ViewModel() {

    protected var useCaseList: MutableList<UseCase> = mutableListOf()

    init {
        useCaseList.addAll(useCases)
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }
}