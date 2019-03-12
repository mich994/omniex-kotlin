package omniex.nl.omniex.ui.base

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView> : MvpBasePresenter<V>() {

    private val mCompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun detachView() {
        super.detachView()
        mCompositeDisposable.dispose()
    }
}