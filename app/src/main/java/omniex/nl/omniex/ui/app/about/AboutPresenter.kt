package omniex.nl.omniex.ui.app.about

import omniex.nl.omniex.data.repository.AboutRepository
import omniex.nl.omniex.ui.base.BasePresenter
import retrofit2.Response
import javax.inject.Inject

class AboutPresenter @Inject
internal constructor(private val mAboutRepository: AboutRepository) : BasePresenter<AboutView>() {

    fun getInformation() {
        addDisposable(mAboutRepository
                .information()
                .subscribe({ t1: Response<Void>?, t2: Throwable? -> t2!!.printStackTrace()}))
    }

    fun getInformationById(id: Int?) {
        addDisposable(mAboutRepository
                .getInformationById(id)
                .subscribe({ t1: Response<Void>?, t2: Throwable? -> t2!!.printStackTrace()}))
    }
}
