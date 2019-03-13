package omniex.nl.omniex.data.api

import io.reactivex.Single
import omniex.nl.omniex.data.model.address.AddAddress
import omniex.nl.omniex.data.model.address.Address
import omniex.nl.omniex.data.model.auth.Login
import omniex.nl.omniex.data.model.auth.OldToken
import omniex.nl.omniex.data.model.auth.register.RegisterSetter
import omniex.nl.omniex.data.model.cart.AddToCartModel
import omniex.nl.omniex.data.model.cart.CartItemDelete
import omniex.nl.omniex.data.model.cart.CartQuantitySetter
import omniex.nl.omniex.data.model.order.OrderAddress
import omniex.nl.omniex.data.model.payment.PaymentMethodSetter
import omniex.nl.omniex.data.model.response.*
import omniex.nl.omniex.data.model.shipping.ShippingMethodSetter
import omniex.nl.omniex.data.model.shipping.ShippingQuote
import retrofit2.Response
import retrofit2.http.*

interface OmniexApi {

    //region ACCOUNT
    @POST("index.php?route=feed/rest_api/gettoken&grant_type=client_credentials")
    fun getAccessToken(
            @Header("Authorization") header: String
    ): Single<Response<AccessTokenResponse>>

    @POST("index.php?route=feed/rest_api/gettoken&grant_type=client_credentials")
    fun refreshAccessToken(
            @Header("Authorization") header: String,
            @Body oldToken: OldToken
    ): Single<Response<AccessTokenResponse>>

    @POST("index.php?route=rest/login/login")
    fun login(
            @Header("Authorization") accessToken: String,
            @Body login: Login
    ): Single<Response<LoginResponse>>

    @POST("index.php?route=rest/logout/logout")
    fun logout(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @POST("index.php?route=rest/register/register")
    fun register(
            @Header("Authorization") accessToken: String,
            @Body registerSetter: RegisterSetter
    ): Single<Response<RegisterResponse>>
    //endregion

    //region CATEGORIES
    @GET("index.php?route=feed/rest_api/categories")
    fun getCategories(
            @Header("Authorization") accessToken: String
    ): Single<Response<CategoriesResponse>>
    //endregion

    //region PRODUCTS
    @GET("index.php?route=feed/rest_api/products")
    fun getProducts(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @GET("index.php?route=feed/rest_api/products")
    fun getProductsByCat(
            @Header("Authorization") accessToken: String,
            @Query("category") catId: Int?
    ): Single<Response<ProductsListResponse>>

    @GET("index.php?route=feed/rest_api/featured")
    fun getListOfFeatured(
            @Header("Authorization") accessToken: String
    ): Single<Response<FeaturedProductsResponse>>

    @GET("index.php?route=feed/rest_api/products")
    fun getProductDetails(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<ProductResponse>>

    @GET("index.php?route=feed/rest_api/bestsellers")
    fun getBestsellers(
            @Header("Authorization") accessToken: String,
            @Query("limit") limit: Int?
    ): Single<Response<ProductsListResponse>>

    //endregion

    //region CART
    @POST("index.php?route=rest/cart/cart")
    fun addToCart(
            @Header("Authorization") accessToken: String,
            @Body addToCartModel: AddToCartModel
    ): Single<Response<Void>>

    @GET("index.php?route=rest/cart/cart")
    fun getCart(
            @Header("Authorization") accessToken: String
    ): Single<Response<CartResponse>>

    @HTTP(method = "DELETE", path = "index.php?route=rest/cart/cart", hasBody = true)
    fun deleteCartItem(
            @Header("Authorization") accessToken: String,
            @Body cartItemDelete: CartItemDelete
    ):
    //    @DELETE("index.php?route=rest/cart/cart")
            Single<Response<Void>>

    @DELETE("index.php?route=rest/cart/emptycart")
    fun emptyCart(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/cart/cart")
    fun updateCartItemQuantity(
            @Header("Authorization") accessToken: String,
            @Body cartQuantitySetter: CartQuantitySetter
    ): Single<Response<Void>>
    //endregion

    //region SHIPPING
    @GET("index.php?route=rest/shipping_method/shippingmethods")
    fun getShippingMethods(
            @Header("Authorization") accessToken: String
    ): Single<Response<ShippingMethodResponse>>

    @POST("index.php?route=rest/shipping_method/shippingmethods")
    fun setShippingMethod(
            @Header("Authorization") accessToken: String,
            @Body shippingMethodSetter: ShippingMethodSetter
    ): Single<Response<Void>>

    @POST("index.php?route=rest/cart/shippingquotes")
    fun getShippingQuotes(
            @Header("Authorization") accessToken: String,
            @Body shippingQuote: ShippingQuote
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/cart/shippingquotes")
    fun saveShippingQuote(
            @Header("Authorization") accessToken: String,
            @Query("id") id: String
    ): Single<Response<Void>>

    @GET("index.php?route=rest/shipping_address/shippingaddress")
    fun getShippingAddresses(
            @Header("Authorization") accessToken: String
    ): Single<Response<OrderAddressesResponse>>

    @POST("index.php?route=rest/shipping_address/shippingaddress")
    fun addNewShippingAddress(
            @Header("Authorization") accessToken: String,
            @Body orderAddress: OrderAddress
    ): Single<Response<Void>>

    @POST("index.php?route=rest/shipping_address/shippingaddress/existing")
    fun setExistingShippingAddress(
            @Header("Authorization") accessToken: String,
            @Body existingAddress: Address
    ): Single<Response<Void>>
    //endregion

    //region ADDRESS
    @GET("index.php?route=rest/account/address")
    fun getAddressList(
            @Header("Authorization") accessToken: String
    ): Single<Response<AddressListResponse>>

    @POST("index.php?route=rest/account/address")
    fun addNewAddress(
            @Header("Authorization") accessToken: String,
            @Body addAddress: AddAddress
    ): Single<Response<Void>>

    @GET("index.php?route=rest/account/address")
    fun getAddressById(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/account/address")
    fun editAddress(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?,
            @Body addAddress: AddAddress
    ): Single<Response<Void>>

    @DELETE("index.php?route=rest/account/address")
    fun removeAddress(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<Void>>
    //endregion

    //region ORDERS
    @GET("index.php?route=rest/order/orders")
    fun getCustomerOrders(
            @Header("Authorization") accessToken: String,
            @Query("limit") limit: Int?,
            @Query("page") page: Int?
    ): Single<Response<Void>>

    @GET("index.php?route=rest/order/orders")
    fun getOrderDetails(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<Void>>

    @GET("index.php?route=rest/order/orders")
    fun getOrderStatuses(
            @Header("Authorization") accessToken: String,
            @Query("limit") limit: Int?,
            @Query("page") page: Int?
    ): Single<Response<OrderStatusesResponse>>

    @POST("index.php?route=rest/simple_confirm/confirm")
    fun simpleConfirmOverview(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/simple_confirm/confirm")
    fun simpleConfirmSave(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @POST("index.php?route=rest/confirm/confirm")
    fun getOrderOverview(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/confirm/confirm")
    fun confirmOrder(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>
    //endregion

    //region PAYMENTS

    @GET("index.php?route=rest/payment_address/paymentaddress")
    fun getPaymentAdresses(
            @Header("Authorization") accessToken: String
    ): Single<Response<OrderAddressesResponse>>

    @POST("index.php?route=rest/payment_address/paymentaddress")
    fun addNewPaymentAddress(
            @Header("Authorization") accessToken: String,
            @Body orderAddress: OrderAddress
    ): Single<Response<Void>>

    @POST("index.php?route=rest/payment_address/paymentaddress/existing")
    fun setExisitingPaymentAddress(
            @Header("Authorization") accessToken: String,
            @Body existingAddress: Address
    ): Single<Response<Void>>

    @GET("index.php?route=rest/payment_method/payments")
    fun getPaymentMethods(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @POST("index.php?route=rest/payment_method/payments")
    fun setPaymentMethod(
            @Header("Authorization") accessToken: String,
            @Body paymentMethodSetter: PaymentMethodSetter
    ): Single<Response<Void>>

    //endregion

    //region COUNTRIES
    @GET("index.php?route=feed/rest_api/countries")
    fun getCountries(
            @Header("Authorization") accessToken: String
    ): Single<Response<CountryResponse>>

    @GET("index.php?route=feed/rest_api/countries")
    fun getZones(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<ZoneResponse>>
    //endregion

    //region MANUFACTURERS
    @GET("index.php?route=feed/rest_api/manufacturers")
    fun getManufacturers(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>
    //endregion

    //region OTHERS

    @GET("index.php?route=feed/rest_api/information")
    fun getInformation(
            @Header("Authorization") accessToken: String
    ): Single<Response<Void>>

    @GET("index.php?route=feed/rest_api/information")
    fun getInformationById(
            @Header("Authorization") accessToken: String,
            @Query("id") id: Int?
    ): Single<Response<Void>>

    @PUT("index.php?route=rest/account/newsletter")
    fun subscribeNewsletter(
            @Header("Authorization") accessToken: String,
            @Query("subscribe") subscribe: Int?
    ): Single<Response<Void>>

    //endregion
}

























