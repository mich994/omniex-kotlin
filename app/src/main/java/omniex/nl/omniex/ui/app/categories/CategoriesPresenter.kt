package omniex.nl.omniex.ui.app.categories


import omniex.nl.omniex.data.repository.ProductsRepository
import omniex.nl.omniex.ui.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

class CategoriesPresenter @Inject
internal constructor(private val mProductsRepository: ProductsRepository) : BasePresenter<CategoriesView>() {

    internal fun getCategories() {
        ifViewAttached { it.startLoading() }
        addDisposable(mProductsRepository
                .categories()
                .subscribe(
                        { categoriesResponse ->
                            if (categoriesResponse.isSuccessful() && categoriesResponse.code() === 200) {
                                ifViewAttached { view -> view.onCategoriesFetched(categoriesResponse.body()!!.categoryList!!) }
                            }
                            ifViewAttached { it.stopLoading() }
                        }, { error ->
                    ifViewAttached { it.stopLoading() }
                    Timber.e(error)
                }))
    }

}
