package currencyconverter

import android.app.Application
import currencyconverter.di.ApplicationComponent
import currencyconverter.di.ApplicationModule
import currencyconverter.di.DaggerApplicationComponent


class CurrencyConverter : Application(){

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object{
        lateinit var component: ApplicationComponent
    }
}