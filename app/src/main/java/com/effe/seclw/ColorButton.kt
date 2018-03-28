package com.effe.seclw

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.content.res.TypedArray
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.Gravity
import android.widget.LinearLayout





/**
 * Created by efeyucesoy on 3/28/18.
 */
class ColorButton: View {
    constructor(context: Context?, attrs: AttributeSet?):super(context, attrs){
        val a = context!!.obtainStyledAttributes(attrs,
                R.styleable.ColorButton, 0, 0)
        val valueColor = a.getColor(R.styleable.ColorButton_valueColor,
                resources.getColor(android.R.color.holo_red_dark))
        a.recycle()

    }
    constructor(context: Context?):super(context){

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }
}