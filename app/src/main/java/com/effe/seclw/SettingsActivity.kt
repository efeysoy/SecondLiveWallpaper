package com.effe.seclw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*
import android.app.WallpaperManager
import android.content.ComponentName
import android.service.wallpaper.WallpaperService
import android.os.Build



class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btnPreview.setOnClickListener {
//            val i = Intent(this@SettingsActivity, Wallpaper::class.java)
//            if (Build.VERSION.SDK_INT > 15) {
//                i.action = WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER
//                val pkg = WallpaperService::class.java.`package`.name
//                val cls = WallpaperService::class.java.canonicalName
//                i.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(pkg, cls))
//            } else {
//                i.action = WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER
//            }
//            startActivityForResult(i, 0)
            val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                    ComponentName(baseContext, Wallpaper::class.java!!))
            startActivityForResult(intent, 0)
        }
    }
}
