package com.effe.seclw

import android.graphics.PointF
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * Created by efeyucesoy on 3/27/18.
 */
class Dot(p: PointF, size:Float, sr:ShapeRenderer){
    private var actualSize = 0.0f
    private var growth = size / 10
    private var alpha = 1.0f
    private val p = p
    private val size = size
    private var sr = sr

    public val finished: Boolean
        get() {
            return (alpha <= 0)
        }

    fun draw(delta:Float, edges:Int){
        val dt = delta
        if (this.finished)
            return

        actualSize += growth * dt
        sr.color.a = alpha

        if (actualSize > size) {
            actualSize += 2 * growth * dt
            alpha -= 0.1f * dt
        }

        sr.circle(p.x, p.y, actualSize, edges)

    }
}