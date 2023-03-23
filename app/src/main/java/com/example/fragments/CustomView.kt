package com.example.fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CustomView(context: Context) : View(context) {

    private val markingPaint = Paint()
    companion object{
        private const val rectangleLineMargin = 15F
        private const val whiteColor = Color.WHITE
        private  const val LineWidth = 10.0f
        private const val circleRadius = 250.0f

    }
    init {
        markingPaint.color = whiteColor
        markingPaint.style = Paint.Style.STROKE
        markingPaint.strokeWidth = LineWidth
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.GREEN)
        canvas.drawRect(
            left + rectangleLineMargin, top + rectangleLineMargin,
            right - rectangleLineMargin, bottom - rectangleLineMargin,
            markingPaint
        )
        canvas.drawCircle(this.width/2f,this.height/2f, circleRadius, markingPaint )
        canvas.drawRect(
            left + this.height/2f - 55, top + rectangleLineMargin,
            right -  this.height/2f + 55, bottom - this.height + 150f,
            markingPaint
        )
        canvas.drawRect(
            left + this.height/2f - 55, top + this.height - 150f,
            right -  this.height/2f + 55, bottom - rectangleLineMargin,
            markingPaint
        )
        canvas.drawRect(
            left + rectangleLineMargin, top + this.height/2f,
            right - rectangleLineMargin , bottom - this.height/2f,
            markingPaint
        )
    }
}