package currencyconverter.di

import android.content.Context
import currencyconverter.MainActivity
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RepoModule::class])
interface ApplicationComponent{
    fun inject(target: MainActivity)

    fun appContext() : Context

    fun retrofit() : Retrofit
}