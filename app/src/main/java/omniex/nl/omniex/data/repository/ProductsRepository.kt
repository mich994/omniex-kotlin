package omniex.nl.omniex.data.repository

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import omniex.nl.omniex.data.api.OmniexApi
import omniex.nl.omniex.data.model.response.CategoriesResponse
import omniex.nl.omniex.data.model.response.FeaturedProductsResponse
import omniex.nl.omniex.data.model.response.ProductResponse
import omniex.nl.omniex.data.model.response.ProductsListResponse
import omniex.nl.omniex.utils.SharedPrefUtils
import retrofit2.Response
import javax.inject.Inject

class ProductsRepository @Inject
internal constructor(private val mOmniexApi: OmniexApi, private val mSharedPrefUtils: SharedPrefUtils) {

    fun categories(): Single<Response<CategoriesResponse>> = mOmniexApi
                .getCategories(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun products(): Single<Response<Void>> = mOmniexApi
                .getProducts(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


    fun listOfFeatured(): Single<Response<FeaturedProductsResponse>> = mOmniexApi
                .getListOfFeatured(mSharedPrefUtils.`accessToken()`)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun bestsellers(): Single<Response<ProductsListResponse>> = mOmniexApi
                .getBestsellers(mSharedPrefUtils.`accessToken()`, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getProductsByCat(catId: Int?): Single<Response<ProductsListResponse>> {
        return mOmniexApi
                .getProductsByCat(mSharedPrefUtils.`accessToken()`, catId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProductDetails(id: Int?): Single<Response<ProductResponse>> {
        return mOmniexApi
                .getProductDetails(mSharedPrefUtils.`accessToken()`, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
