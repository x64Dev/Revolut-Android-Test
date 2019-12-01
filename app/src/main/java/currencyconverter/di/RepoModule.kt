package currencyconverter.di

import currencyconverter.ExchangeRepoImpl
import currencyconverter.repo.ExchangeRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepoModule{
    @Singleton
    @Provides
    fun provideExchangeRepo(retrofit: Retrofit) : ExchangeRepo {
        return ExchangeRepoImpl(retrofit)
    }
}