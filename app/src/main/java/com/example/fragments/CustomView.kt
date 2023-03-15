package com.example.fragments

import android.content.Context
import android.graphics.Canvas
import android.Manifest
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.app.ActivityCompat

class CustomView(context: Context?) : View(context) {

    private val markingPaint = Paint()

    init {
        markingPaint.color = Color.WHITE // цвет разметки
        markingPaint.style = Paint.Style.STROKE // обводка вместо сплошной заливки
        markingPaint.strokeWidth = 10.0f // толщина окантовки
    }
    private val margin = 15F
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        val locationPermission = Manifest.permission.BATTERY_STATS
        val hasPermission = ActivityCompat.checkSelfPermission(context, locationPermission)
        Log.d("view", "pressed")
        return true
    }
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
        canvas.drawCircle(this.width/2f,this.height/2f, 25.0F, markingPaint )

    }
}