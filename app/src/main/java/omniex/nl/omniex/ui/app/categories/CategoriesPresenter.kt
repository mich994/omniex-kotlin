package omniex.nl.omniex.ui.app.categories

import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.ui.base.BaseView
import javax.inject.Inject


import timber.log.Timber

class CategoriesPresenter @Inject
internal constructor(private val mProductsRepository: ProductsRepository) : BasePresenter<CategoriesView>() {

    internal fun getCategories() {
        ifViewAttached(v)
        addDisposable(mProductsRepository
                .getCategories()
                .subscribe(
                        { categoriesResponse ->
                            if (categoriesResponse.isSuccessful() && categoriesResponse.code() === 200) {
                                ifViewAttached { view -> view.onCategoriesFetched(categoriesResponse.body().getCategoryList()) }
                            }
                            ifViewAttached(???({ BaseView.stopLoading() }))
                        }, { error ->
                    ifViewAttached(???({ BaseView.stopLoading() }))
                    Timber.e(error)
                }))
    }

}
