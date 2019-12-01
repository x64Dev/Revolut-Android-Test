package currencyconverter.usecases

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<T, in Params> : UseCase() {

    internal abstract fun buildObservableUseCase(params: Params): Observable<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onFinished: () -> Unit = {},
        params: Params
    ) {
        disposeLast()
        lastDisposable = buildObservableUseCase(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate(onFinished)
            .subscribe(onSuccess, onError)
        lastDisposable?.let { compositeDisposable.add(it) }
    }
}