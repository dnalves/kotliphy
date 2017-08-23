package com.dnalves.giphy.common

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class KotliphyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }

}
