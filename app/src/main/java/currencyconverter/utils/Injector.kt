package currencyconverter.utils

import currencyconverter.CurrencyConverter
import currencyconverter.di.ApplicationComponent

class Injector {

    companion object{
        fun get() : ApplicationComponent{
            return CurrencyConverter.component
        }
    }

}