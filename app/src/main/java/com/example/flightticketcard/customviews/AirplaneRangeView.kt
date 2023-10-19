package com.example.flightticketcard.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import com.example.flightticketcard.R
import kotlin.math.min

class AirplaneRangeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val airplane =
        ResourcesCompat.getDrawable(resources, R.drawable.ic_airplane, null)


    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 4f
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(16f, 3f), 0f)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        // Measure height

        val mMeasuredHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                height
            }

            MeasureSpec.AT_MOST -> {
                min(120, height)
            }

            else -> {
                300
            }
        }

        setMeasuredDimension(width, mMeasuredHeight) //it must call at least
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val firstCx = paddingStart + 16f + marginStart
        val cy = (paddingTop + paddingBottom).toFloat()
        val endCx = width - (24f + paddingEnd + marginEnd)

        airplane?.let {

            airplane.setBounds(
                width / 2 - airplane.intrinsicWidth,
                (cy - airplane.intrinsicHeight).toInt(),
                width / 2,
                cy.toInt()
            )
            airplane.draw(canvas)
        }


        canvas.drawCircle(
            firstCx,
            cy,
            24f,
            circlePaint
        )

        canvas.drawCircle(
            endCx,
            cy,
            24f,
            circlePaint
        )

        canvas.drawLine(
            firstCx,
            cy,
            endCx - 24f,
            cy,
            linePaint
        )
    }
}