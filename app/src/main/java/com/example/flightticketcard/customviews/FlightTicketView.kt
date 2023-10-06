package com.example.flightticketcard.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.flightticketcard.R
import com.example.flightticketcard.databinding.LayoutFlightTicketBinding

class FlightTicketView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = LayoutFlightTicketBinding.inflate(
        LayoutInflater.from(context), this
    )

    private val cardBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(ticketBackgroundColor, null)
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(separatorLineColor, null)
        strokeWidth = 4f
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(16f, 3f), 0f)
    }

    var ticketBackgroundColor = R.color.gray
        set(value) {
            field = value
            invalidate()
        }

    var separatorLineColor = R.color.black
        set(value) {
            field = value
            invalidate()
        }

    init {
        setWillNotDraw(false)
        init()
    }

    private fun init() {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FlightTicketView)

        setFromWhere(attributes.getString(R.styleable.FlightTicketView_from).orEmpty())

        setToWhere(attributes.getString(R.styleable.FlightTicketView_to).orEmpty())

        setFlightTime(attributes.getString(R.styleable.FlightTicketView_flight_time).orEmpty())

        setStartDateTime(attributes.getString(R.styleable.FlightTicketView_start_date_time).orEmpty())

        setEndDateTime(attributes.getString(R.styleable.FlightTicketView_end_date_time).orEmpty())

        val icon = attributes.getDrawable(R.styleable.FlightTicketView_company_icon)
        setCompanyIcon(icon = icon)

        setFlightNumber(attributes.getString(R.styleable.FlightTicketView_flight_number).orEmpty())

        setPrice(attributes.getString(R.styleable.FlightTicketView_price).orEmpty())

        attributes.recycle()
    }

    fun setFromWhere(from: String) {
        binding.tvFrom.text = from.take(3).uppercase()
        binding.tvFromFull.text = from
    }

    fun setToWhere(to: String) {
        binding.tvTo.text = to.take(3).uppercase()
        binding.tvToFull.text = to
    }

    fun setFlightTime(time: String) {
        binding.tvTime.text = time
    }

    fun setStartDateTime(dateTime: String) {
        binding.tvStartDatetime.text = dateTime
    }

    fun setEndDateTime(dateTime: String) {
        binding.tvEndDatetime.text = dateTime
    }

    fun setCompanyIcon(icon: Drawable?) {
        binding.ivAirwayIcon.setImageDrawable(icon)
    }

    fun setFlightNumber(flightNumber: String) {
        binding.tvFlightNumber.text = flightNumber
    }

    fun setPrice(price: String) {
        binding.tvTicketPrice.text = price
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        cardBackgroundPaint.color = resources.getColor(ticketBackgroundColor, null)
        linePaint.color = resources.getColor(separatorLineColor, null)


        canvas.apply {

            drawRoundRect(
                width.toFloat(),
                height.toFloat(),
                0f, 0f,
                32f, 32f,
                cardBackgroundPaint
            )

            drawLine(
                0f, height - (height * 0.25f),
                (right - left).toFloat(),
                height - (height * 0.25f),
                linePaint
            )

            drawCircle(
                0f, height - (height * 0.25f), 48f, circlePaint
            )

            drawCircle(
                (right - left).toFloat(), height - (height * 0.25f), 48f, circlePaint
            )
        }
    }
}