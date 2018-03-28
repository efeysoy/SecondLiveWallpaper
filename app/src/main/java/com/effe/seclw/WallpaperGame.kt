package com.effe.seclw

import android.content.SharedPreferences
import android.graphics.Point
import android.graphics.PointF
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by efeyucesoy on 3/23/18.
 */
class WallpaperGame(sp: SharedPreferences) : Game(), SharedPreferences.OnSharedPreferenceChangeListener {
    internal lateinit var font:BitmapFont
    internal lateinit var batch:SpriteBatch
    internal lateinit var sr:ShapeRenderer
    internal lateinit var color:Color
    private var winheight: Int = 0
    private var winwidth:Int = 0
    private var speed = 15
    private var edges = 50
    val dots:ArrayList<Dot> = ArrayList()
    var sp = sp

    val maxDots = 20
    val rand = Random()

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        color.r = p0!!.getInt("red", 255) / 255f
        color.g = p0!!.getInt("green", 0) / 255f
        color.b = p0!!.getInt("blue", 0) / 255f
        speed = p0!!.getInt("speed", 15)
        edges = p0!!.getInt("edges", 50)
    }

    override fun create() {
        font = BitmapFont()
        batch = SpriteBatch()
        sr = ShapeRenderer()
        color = Color()

        color.r = sp.getInt("red", 255) / 255f
        color.g = sp.getInt("green", 0) / 255f
        color.b = sp.getInt("blue", 0) / 255f
        speed = sp.getInt("speed", 15)
        edges = sp.getInt("edges", 50)

        sp.registerOnSharedPreferenceChangeListener(this)

        createNewDots()
    }

    fun createNewDots(){
        winwidth = Gdx.graphics.width
        winheight = Gdx.graphics.height

        if (maxDots > dots.count() && rand.nextFloat() > 0.8) {
            val tmpdot = Dot(PointF(rand.nextFloat() * winwidth, rand.nextFloat() * winheight), rand.nextFloat() * 50 + 5, sr)
            dots.add(tmpdot)
        }
    }

    fun removeFinishedDots(){
        dots.removeIf {
            dot -> dot.finished
        }
//        for(dot in dots)
//        {
//            if (dot.finished)
//                dots.remove(dot)
//        }
    }

    override fun render() {
        super.render()
        Gdx.gl.glClearColor(0f, 0f, 0f, 0.1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glLineWidth(4f)
        //sr.setProjectionMatrix(camera.combined)
        sr.begin(ShapeRenderer.ShapeType.Line)
        sr.color = color

        for(dot in dots)
            dot.draw(Gdx.graphics.deltaTime * speed, edges)

        sr.end()

        removeFinishedDots()
        createNewDots()

        batch.begin()

        font.draw(batch, java.lang.Integer.toString(dots.count()), 10f, 10f)

        batch.end()
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    override fun pause() {
        super.pause()
        sp.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun resume() {
        super.resume()
        sp.registerOnSharedPreferenceChangeListener(this)
    }
}