package currencyconverter.di

import android.app.Application
import android.content.Context
import currencyconverter.domain.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Singleton
    @Provides
    fun provideContext(application : Application) : Context = application.applicationContext

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.CURRENCY_EXCHANGE_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesApplication() : Application{
        return application
    }
}