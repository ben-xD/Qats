package uk.orth.qats

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class QatsApp : Application() {
    override fun onCreate() {
        Timber.plant()
        super.onCreate()
    }
}