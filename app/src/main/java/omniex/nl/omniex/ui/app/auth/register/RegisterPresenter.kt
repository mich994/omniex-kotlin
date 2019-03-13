package omniex.nl.omniex.ui.app.auth.register

import com.google.gson.Gson
import omniex.nl.omniex.data.model.auth.register.RegisterSetter
import omniex.nl.omniex.data.model.response.RegisterResponse
import omniex.nl.omniex.data.repository.ProfileRepository
import omniex.nl.omniex.ui.base.BasePresenter
import omniex.nl.omniex.utils.StringUtils
import timber.log.Timber
import javax.inject.Inject

class RegisterPresenter @Inject
internal constructor(private val mProfileRepository: ProfileRepository) : BasePresenter<RegisterView>() {

    internal fun register(registerSetter: RegisterSetter) {
        addDisposable(mProfileRepository
                .register(registerSetter)
                .subscribe(
                        { voidResponse ->
                            if (voidResponse.code() === 200) {
                                ifViewAttached { view -> view.onRegisterSuccess() }
                            } else {
                                val error = voidResponse.errorBody().toString()
                                ifViewAttached { view -> view.onRegisterFailed(StringUtils.createErrorMessageString(errorResponse(error).errors)) }
                            }
                        },
                        { error ->
                            ifViewAttached { view -> view.onConnectionFailed() }
                            Timber.e(error)
                            error.printStackTrace()
                        }))
    }

    private fun errorResponse(errorBody: String): RegisterResponse {
        return Gson().fromJson<RegisterResponse>(errorBody, RegisterResponse::class.java!!)
    }
}
