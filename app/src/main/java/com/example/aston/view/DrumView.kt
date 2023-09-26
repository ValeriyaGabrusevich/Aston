package com.example.aston.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.example.aston.ImageProvider
import com.example.aston.R
import com.example.aston.data.Arc
import com.example.aston.data.Color
import kotlin.math.ceil

class DrumView
@JvmOverloads
constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

	private val circlePaddingBottom = resources.getDimensionPixelSize(R.dimen.circle_padding_bottom)
	private val triangleSideLength = resources.getDimension(R.dimen.triangle_side_length)
	private val labelTextSize = resources.getDimension(R.dimen.label_text_size)
	private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		style = Paint.Style.FILL
	}
	private val trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		style = Paint.Style.FILL
		color = ContextCompat.getColor(context, R.color.purple_200)
	}

	private var labelTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		textSize = labelTextSize
		textAlign = Paint.Align.CENTER
	}

	private val arcs = mutableListOf<Arc>()
	private val rect = RectF()
	private val circleSizeInPercent = 1f
	private var centerPoint = PointF(0f, 0f)
	private val trianglePath = Path()
	private var sweepAngle = 360f / Color.values().size
	private var currentSweepAngle = 0f

	private var currentColor: Color? = null
	private var isFirstSpin = true

	private var randomValue = 0f
	private var random = 0f
	private var imageProvider: ImageProvider? = null

	private val animator = ValueAnimator().apply {
		duration = 400L
		addUpdateListener { valueAnimator ->
			currentSweepAngle = valueAnimator.animatedValue as Float
			invalidate()
		}
		doOnEnd {
			setTextOrImageView()
		}
	}

	init {
		calculateArcs()
		setTextOrImageView()
	}

	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		val desiredHeight =
			suggestedMinimumHeight + paddingBottom + paddingTop + circlePaddingBottom
		val desiredWidth = suggestedMinimumWidth + paddingLeft + paddingRight
		setMeasuredDimension(
			calculateDefaultSize(desiredWidth, widthMeasureSpec, false),
			calculateDefaultSize(desiredHeight, heightMeasureSpec, true)
		)
	}

	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		centerPoint.set(PointF(w / 2f, (h - circlePaddingBottom) / 2f))
		rect.set(
			centerPoint.x - w.toFloat() / 2 * circleSizeInPercent,
			centerPoint.y - (h.toFloat() - circlePaddingBottom) / 2 * circleSizeInPercent,
			centerPoint.x + w.toFloat() / 2 * circleSizeInPercent,
			centerPoint.y + (h.toFloat() - circlePaddingBottom) / 2 * circleSizeInPercent,
		)
		calculateTrianglePath()
	}

	override fun onDraw(canvas: Canvas) {
		arcs.forEach { arc ->
			paint.color = ContextCompat.getColor(context, arc.color)
			canvas.drawArc(
				rect,
				currentSweepAngle + arc.startAngle,
				arc.sweepAngle,
				true,
				paint
			)
		}
		canvas.drawPath(trianglePath, trianglePaint)
		currentColor?.let { color ->
			if (color.image.isNullOrEmpty()) {
				imageProvider?.setImage(null)
			}

			labelTextPaint.color = ContextCompat.getColor(context, color.colorRes)
			canvas.drawText(
				color.text?.let { context.getString(it) }.orEmpty(),
				rect.centerX(),
				rect.bottom + circlePaddingBottom - 6f,
				labelTextPaint
			)

		}
	}

	fun setOnImageProvider(provider: ImageProvider) {
		imageProvider = provider
	}

	fun animateRect() {
		randomValue = (180..360).random().toFloat()
		random = randomValue + currentSweepAngle
		animator.setFloatValues(currentSweepAngle, random)
		animator.start()
	}

	fun clearText() {
		currentColor = null
		invalidate()
	}

	private fun setTextOrImageView() {
		val kef = if (isFirstSpin) {
			currentSweepAngle / 360
		} else if (currentSweepAngle / 360 < 1) {
			1f
		} else {
			currentSweepAngle / 360
		}
		var kek = if (isFirstSpin) {
			90f
		} else {
			ceil(kef) * 360f - currentSweepAngle + 90
		}

		if (kek > 360) {
			kek -= 360
		}
		val activeArc = arcs.firstOrNull { arc ->
			kek in (arc.startAngle..((arc.startAngle + arc.sweepAngle)))
		}
		currentColor = Color.values().find { color -> color.colorRes == activeArc?.color }
		currentColor?.image?.let {
			imageProvider?.setImage(it)
		}
		isFirstSpin = false
		invalidate()
	}

	private fun calculateDefaultSize(desiredSize: Int, measureSpec: Int, isHeight: Boolean): Int {
		val size = MeasureSpec.getSize(measureSpec)
		return when (MeasureSpec.getMode(measureSpec)) {
			MeasureSpec.EXACTLY -> if (isHeight) desiredSize + size else size
			MeasureSpec.AT_MOST -> desiredSize.coerceAtMost(size)
			else -> desiredSize
		}
	}

	private fun calculateArcs() {
		Color.values().forEachIndexed { index, color ->
			val startAngle = index * sweepAngle
			arcs.add(Arc(startAngle, sweepAngle, color.colorRes))
		}
	}

	private fun calculateTrianglePath() {
		trianglePath.reset()
		trianglePath.moveTo(
			rect.centerX(),
			rect.bottom - triangleSideLength * circleSizeInPercent / 2
		)
		trianglePath.lineTo(
			rect.centerX() - triangleSideLength * circleSizeInPercent / 2,
			rect.bottom + triangleSideLength * circleSizeInPercent
		)
		trianglePath.lineTo(
			rect.centerX() + triangleSideLength * circleSizeInPercent / 2,
			rect.bottom + triangleSideLength * circleSizeInPercent
		)
		trianglePath.lineTo(
			rect.centerX(),
			rect.bottom - triangleSideLength * circleSizeInPercent / 2
		)
	}
}