package com.example.fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomView(context: Context?) : View(context) {

    private val markingPaint = Paint()

    init {
        markingPaint.color = Color.WHITE
        markingPaint.style = Paint.Style.STROKE
        markingPaint.strokeWidth = 10.0f
    }
    private val margin = 15F

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN)
        canvas.drawRect(
            left + margin, top + margin,
            right - margin, bottom - margin,
            markingPaint
        )
        canvas.drawCircle(this.width/2f,this.height/2f, 250.0F, markingPaint )
        canvas.drawRect(
            left + this.height/2f - 55, top + margin,
            right -  this.height/2f + 55, bottom - this.height + 150f,
            markingPaint
        )
        canvas.drawRect(
            left + this.height/2f - 55, top + this.height - 150f,
            right -  this.height/2f + 55, bottom - margin,
            markingPaint
        )
        canvas.drawRect(
            left + margin, top + this.height/2f,
            right - margin , bottom - this.height/2f,
            markingPaint
        )
    }
}