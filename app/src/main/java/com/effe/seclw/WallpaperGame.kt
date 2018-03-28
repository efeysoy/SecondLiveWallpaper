package com.effe.seclw

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
class WallpaperGame : Game(){
    internal lateinit var font:BitmapFont
    internal lateinit var batch:SpriteBatch
    internal lateinit var sr:ShapeRenderer
    private var winheight: Int = 0
    private var winwidth:Int = 0
    val dots:ArrayList<Dot> = ArrayList()

    val maxDots = 20
    val rand = Random()

    override fun create() {
        font = BitmapFont()
        batch = SpriteBatch()
        sr = ShapeRenderer()

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
        sr.color = Color.BLACK
        //sr.setProjectionMatrix(camera.combined)
        sr.begin(ShapeRenderer.ShapeType.Line)
        sr.color = Color(1f,0f,0f,0.3f)

        for(dot in dots)
            dot.draw(Gdx.graphics.deltaTime)

        sr.end()

        removeFinishedDots()
        createNewDots()

        batch.begin()

        font.draw(batch, java.lang.Integer.toString(dots.count()), 10f, 10f)

        batch.end()
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}