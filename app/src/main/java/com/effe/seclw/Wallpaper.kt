package com.effe.seclw

import android.content.Context
import android.content.res.Configuration
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidLiveWallpaper
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService

/**
 * Created by efeyucesoy on 3/23/18.
 */
class Wallpaper : AndroidLiveWallpaperService() {
    override fun onCreateApplication() {
        super.onCreateApplication()

        val config = AndroidApplicationConfiguration()

        config.numSamples = 3

        initialize(WallpaperGame(getSharedPreferences("com.effe.seclw", 0)), config)
    }


}