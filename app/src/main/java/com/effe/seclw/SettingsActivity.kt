package com.effe.seclw

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*
import android.app.WallpaperManager
import android.content.ComponentName
import android.graphics.Color
import android.widget.SeekBar
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import android.widget.Toast
import android.widget.SeekBar.OnSeekBarChangeListener



class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val cp = ColorPicker(this)
        val prefs = getSharedPreferences("com.effe.seclw", 0)
        val editor = prefs.edit()
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

        cp.color = Color.rgb(prefs.getInt("red", 255),
                prefs.getInt("green", 0),
                prefs.getInt("blue", 0))


        btnColor.setOnClickListener {
            cp.show()
            cp.setCallback {
                btnColor.background.setTint(it)
                editor.putInt("red", Color.red(it))
                editor.putInt("green", Color.green(it))
                editor.putInt("blue", Color.blue(it))
                editor.commit()
                cp.hide()
            }
        }

        btnColor.background.setTint(cp.color)

        seekEdges.progress = prefs.getInt("edges", 46)

        seekEdges.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                editor.putInt("edges", progress + 4)
                editor.commit()
            }
        })

        seekSpeed.progress = 15

        seekSpeed.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {}

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                editor.putInt("speed", progress + 1)
                editor.commit()
            }
        })

    }

}

